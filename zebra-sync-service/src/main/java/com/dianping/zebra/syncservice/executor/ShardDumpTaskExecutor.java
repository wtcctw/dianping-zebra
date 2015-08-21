package com.dianping.zebra.syncservice.executor;

import com.dianping.cat.Cat;
import com.dianping.zebra.biz.entity.ShardDumpDbEntity;
import com.dianping.zebra.biz.entity.ShardDumpTaskEntity;
import com.dianping.zebra.biz.service.ShardDumpService;
import com.dianping.zebra.syncservice.util.ProcessBuilderWrapper;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ShardDumpTaskExecutor implements TaskExecutor{
    private List<String> options = Lists
        .newArrayList("--master-data=2", "--disable-keys", "--skip-comments", "--quick", "--add-drop-database=false",
            "--no-create-info", "--add-drop-table=false", "--skip-add-locks", "--default-character-set=utf8",
            "--max_allowed_packet=1073741824", "-i", "--single-transaction", "--hex-blob", "--compact");

    private static final Logger logger = LoggerFactory.getLogger(ShardDumpTaskExecutor.class);

    private static final Charset DEFAULT_CJARSET = Charset.forName("utf8");

    private volatile int indexIncrease = 100000;

    private volatile int MAX_SQL_SIZE = 20;

    private volatile int MIN_SQL_SIZE = 10;

    private ShardDumpService shardDumpService;

    private static final Pattern BINLOG_PATTERN = Pattern
        .compile(".*MASTER_LOG_FILE='([^']+)', MASTER_LOG_POS=(\\d+).*");

    protected final ShardDumpTaskEntity task;

    protected final String dumpOutputDir;

    protected static final long FINISH_INDEX = Long.MIN_VALUE;

    protected volatile ShardDumpDbEntity srcDBInstance;

    protected volatile ShardDumpDbEntity dstDBInstance;

    protected final BlockingQueue<Long> waitForLoadQueue = new LinkedBlockingQueue<Long>(5);

    protected Thread dumpWorker;

    protected Thread loadWorker;

    protected volatile Status dumpStatus = Status.UNKNOWN;

    protected volatile Status loadStatus = Status.UNKNOWN;

    protected volatile int dumpPersent;

    protected volatile int loadPersent;

    public ShardDumpTaskExecutor(ShardDumpTaskEntity task) {
        checkNotNull(task, "task");
        checkNotNull(task.getTableName(), "task.tableName");
        checkNotNull(task.getIndexColumnName(), "task.indexColumnName");

        this.task = task;
        this.dumpOutputDir = "/tmp/" + task.getId() + "/";
        this.srcDBInstance = task.getSrcDbEntity();
        this.dstDBInstance = task.getDstDbEntity();

        this.dumpWorker = new Thread(new DumpWorker());
        this.loadWorker = new Thread(new LoadWorker());
    }

    public boolean isFinish() {
        if (this.task.getIndexKey() == FINISH_INDEX) {
            loadStatus = Status.SUCCESS;
            dumpStatus = Status.SUCCESS;
            return true;
        }
        return false;
    }

    public synchronized void init() {
        if (isFinish()) {
            return;
        }

        createOutPutDir();
    }

    protected void saveTask() {
        this.task.setStatus(getTaskState());
        shardDumpService.updateTaskStatus(this.task);
    }

    protected void createOutPutDir() {
        File theDir = new File(this.dumpOutputDir);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    protected String getDumpFile(long index) {
        return String.format("%s%d-%d.dump.sql", dumpOutputDir, task.getShardRule().hashCode(), index);
    }

    protected void adjustIndexIncrease() {
        int count = 0;
        double size = 0;

        for (File file : FileUtils.listFiles(new File(dumpOutputDir), new String[] { "sql" }, false)) {
            count++;
            size += file.length() / 1024.0f / 1024.0f;//MB
        }

        if (count == 0) {
            return;
        }

        if (size / count < MIN_SQL_SIZE || size / count > MAX_SQL_SIZE) {
            indexIncrease = (int) (indexIncrease * (MAX_SQL_SIZE + MIN_SQL_SIZE) / 2 / (size / count));
        }
    }

    class DumpWorker implements Runnable {
        protected long lastIndex;

        public DumpWorker() {
            this.lastIndex = task.getIndexKey();
        }

        @Override
        public void run() {
            dumpStatus = Status.RUNNING;

            while (true) {
                long nextIndex = increaseIndex();

                try {
                    String output = mysqldump(this.lastIndex, nextIndex);
                    if (!Strings.isNullOrEmpty(output)) {
                        //hack,ignore this warning
                        if (!output
                            .contains("Warning: Using a password on the command line interface can be insecure.")) {
                            throw new IOException(output);
                        }
                    }

                    if (lastIndex > task.getMaxKey()) {
                        dumpPersent = 100;
                        waitForLoadQueue.put(FINISH_INDEX);
                        break;
                    }

                    output = convertTableName(this.lastIndex);
                    if (!Strings.isNullOrEmpty(output)) {
                        throw new IOException(output);
                    }

                    checkAndUpdateBinlogInfo(this.lastIndex);

                    dumpPersent = (int) (this.lastIndex * 100 / task.getMaxKey());
                    waitForLoadQueue.put(lastIndex);
                    this.lastIndex = nextIndex;

                    saveTask();
                } catch (InterruptedException e) {
                    dumpStatus = Status.STOPPED;
                    saveTask();
                    break;
                } catch (Exception e) {
                    String msg = "Dump Failed!";
                    logger.error(msg, e);
                    Cat.logError(msg, e);
                    dumpStatus = Status.FAILED;
                    saveTask();
                    break;
                }
            }
        }

        protected long increaseIndex() {
            adjustIndexIncrease();
            return this.lastIndex + indexIncrease;
        }

        protected String mysqldump(long lastIndex, long nextIndex) throws IOException, InterruptedException {
            checkNotNull(srcDBInstance, "srcDBInstance");

            List<String> cmdlist = new ArrayList<String>();

            //todo: config mysql dump in config
            cmdlist.add("mysqldump");
            cmdlist.add("--host=" + srcDBInstance.getHost());
            cmdlist.add("--port=" + srcDBInstance.getPort());
            cmdlist.add("--user=" + srcDBInstance.getUsername());
            cmdlist.add("--password=" + srcDBInstance.getPassword());
            cmdlist.add("--where=" + String
                .format("%s AND %s > %d AND %s <= %d", task.getShardRule(), task.getIndexColumnName(), lastIndex,
                    task.getIndexColumnName(), nextIndex));

            cmdlist.addAll(options);
            cmdlist.add("--result-file=" + getDumpFile(lastIndex));
            cmdlist.add(task.getDataBase());
            cmdlist.add(task.getTableName());

            return executeByProcessBuilder(cmdlist);
        }

        protected String convertTableName(long index) throws IOException, InterruptedException {
            List<String> cmdlist = new ArrayList<String>();
            cmdlist.add("perl");
            cmdlist.add("-i");
            cmdlist.add("-p");
            cmdlist.add("-e");
            cmdlist
                .add("s/(^INSERT )(INTO )(`)([^`]+)(`)(.*$)/\\1IGNORE \\2\\3" + task.getTargetTableName() + "\\5\\6/g");
            cmdlist.add(getDumpFile(index));
            return executeByProcessBuilder(cmdlist);
        }

        protected void checkAndUpdateBinlogInfo(long index) throws IOException {
            //-- CHANGE MASTER TO MASTER_LOG_FILE='mysqlbin-log.000010', MASTER_LOG_POS=91841517;
            String firstLine = readFirstLine(index);

            Matcher result = BINLOG_PATTERN.matcher(firstLine);
            if (!result.matches() || result.groupCount() != 2) {
                throw new IOException("BINLOG read failed! " + firstLine);
            }

            String binlog = result.group(1);
            long position = Long.parseLong(result.group(2));

            checkAndUpdateBinlogInfo(binlog, position);
        }

        protected void checkAndUpdateBinlogInfo(String binlog, long position) throws IOException {
            boolean needToSet = false;
            boolean needUpdateMaxIndex = false;

            if (Strings.isNullOrEmpty(task.getBinlogFile())) {
                needToSet = true;
                needUpdateMaxIndex = true;
            } else {
                int oldBinlogIndex = getBinlogIndex(task.getBinlogFile());
                int newBinlogIndex = getBinlogIndex(binlog);

                if (newBinlogIndex < oldBinlogIndex || (newBinlogIndex == oldBinlogIndex && position < task
                    .getBinlogPos())) {
                    needToSet = true;
                }
            }

            if (needUpdateMaxIndex) {
                long maxIndex = shardDumpService
                    .getMaxIndex(task.getSrcDbName(), task.getDataBase(), task.getTableName(),
                        task.getIndexColumnName());
                if (task.getMaxKey() < maxIndex) {
                    task.setMaxKey(maxIndex);
                }
            }

            if (needToSet) {
                task.setBinlogFile(binlog);
                task.setBinlogPos(position);
                saveTask();
            }
        }

        protected int getBinlogIndex(String binlog) throws IOException {
            //mysql-bin.0000001
            int index = binlog.lastIndexOf(".");
            if (index < 0 || index == binlog.length() - 1) {
                throw new IOException("binlog file name error: " + binlog);
            }
            return Integer.parseInt(binlog.substring(index + 1));
        }

        protected String readFirstLine(long index) throws IOException {
            return Files.readFirstLine(new File(getDumpFile(index)), DEFAULT_CJARSET);
        }
    }

    class LoadWorker implements Runnable {
        protected void cleanUp(long index) {
            new File(getDumpFile(index)).delete();
            task.setIndexKey(index);
            saveTask();
        }

        @Override
        public void run() {
            loadStatus = Status.RUNNING;

            while (true) {
                try {
                    Long index = waitForLoadQueue.take();
                    if (index == FINISH_INDEX) {
                        cleanUp(index);
                        cleanup();
                        loadPersent = 100;
                        break;
                    }

                    String output = mysqlload(index);
                    if (!Strings.isNullOrEmpty(output)) {
                        //hack,ignore this warning
                        if (!output
                            .contains("Warning: Using a password on the command line interface can be insecure.")) {
                            throw new IOException(output);
                        }
                    }
                    cleanUp(index);

                    loadPersent = (int) (index * 100 / task.getMaxKey());
                    saveTask();
                } catch (InterruptedException e) {
                    loadStatus = Status.STOPPED;
                    saveTask();
                    break;
                } catch (Exception e) {
                    String msg = "Load Failed!";
                    logger.error(msg, e);
                    Cat.logError(msg, e);
                    loadStatus = Status.FAILED;
                    saveTask();
                    break;
                }
            }
        }

        protected String mysqlload(long index) throws IOException, InterruptedException {
            checkNotNull(dstDBInstance, "dstDBInstance");

            List<String> cmdlist = new ArrayList<String>();
            cmdlist.add("mysql -f --default-character-set=utf8");
            cmdlist.add("'--database=" + task.getTargetDataBase() + "'");
            cmdlist.add("'--user=" + dstDBInstance.getUsername() + "'");
            cmdlist.add("'--host=" + dstDBInstance.getHost() + "'");
            cmdlist.add("'--port=" + dstDBInstance.getPort() + "'");
            cmdlist.add("'--password=" + dstDBInstance.getPassword() + "'");
            cmdlist.add("< '" + getDumpFile(index) + "'");

            return executeByProcessBuilder(Lists.newArrayList("sh", "-c", Joiner.on(" ").join(cmdlist)));
        }

    }

    protected String executeByProcessBuilder(List<String> cmd) throws IOException, InterruptedException {
        logger.debug("execute shell script, cmd is: {}", Joiner.on(" ").join(cmd));
        ProcessBuilderWrapper pbd = new ProcessBuilderWrapper(cmd);
        logger.debug("Command has terminated with status: {}", pbd.getStatus());
        if (!Strings.isNullOrEmpty(pbd.getInfos())) {
            logger.debug("Output:\n{}", pbd.getInfos());
        }
        return pbd.getErrors();
    }

    public synchronized void start() {
        if (isFinish()) {
            return;
        }

        dumpWorker.start();
        loadWorker.start();
    }

    public ShardDumpTaskEntity getTask() {
        return this.task;
    }

    public String getTaskState() {
        isFinish();

        if (Status.SUCCESS.equals(dumpStatus) && Status.SUCCESS.equals(loadStatus)) {
            return Status.SUCCESS.getDesc();
        }

        return String.format("dump:%s load:%s (%d%%)", dumpStatus.getDesc(), loadStatus.getDesc(),
            ((loadPersent + dumpPersent) / 2));
    }

    public synchronized void stop() {
        dumpWorker.interrupt();
        loadWorker.interrupt();

        while (dumpWorker.isAlive() || loadWorker.isAlive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
        cleanup();
    }

    public void cleanup() {
        try {
            FileUtils.deleteDirectory(new File(dumpOutputDir));
        } catch (IOException e) {
        }
    }

    public void setShardDumpService(ShardDumpService shardDumpService) {
        this.shardDumpService = shardDumpService;
    }

    enum Status {
        UNKNOWN("未知"),
        RUNNING("运行中"),
        STOPPED("已停止"),
        SUCCESS("已成功"),
        FAILED("已失败");

        private final String desc;

        Status(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
}
