package com.dianping.zebra.syncservice.job.executor;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;
import com.dianping.zebra.syncservice.job.executor.ShardDumpTaskExecutor;

/**
 * Dozer @ 6/1/15
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class ShardDumpTaskExecutorTest {
    ShardDumpTaskEntity task;

    ShardDumpTaskExecutor target;

    ShardDumpTaskExecutor.DumpWorker dumpWorker;

    static final String FILE_NAME = "/tmp/ShardDumpTaskExecutorTest.tmp";

    File file = new File(FILE_NAME);

    @Before
    public void setUp() throws Exception {
        task = new ShardDumpTaskEntity();
        task.setTableName("test");
        task.setIndexColumnName("id");
        target = spy(new ShardDumpTaskExecutor(task));
        dumpWorker = spy(target.new DumpWorker());

        doReturn(FILE_NAME).when(target).getDumpFile(anyInt());

        deleteFile();
    }

    @After
    public void tearDown() throws Exception {
        deleteFile();
    }

    @Test
    public void testGetPositionFromContent() throws Exception {
        doReturn("-- CHANGE MASTER TO MASTER_LOG_FILE='mysqlbin.log.000010', MASTER_LOG_POS=91841517;").when(dumpWorker)
            .readFirstLine(anyInt());
        doNothing().when(dumpWorker).checkAndUpdateBinlogInfo("mysqlbin.log.000010", 91841517);

        dumpWorker.checkAndUpdateBinlogInfo(1);
        verify(dumpWorker, times(1)).readFirstLine(1);
        verify(dumpWorker, times(1)).checkAndUpdateBinlogInfo("mysqlbin.log.000010", 91841517);
    }

    @Test
    public void testBinlogNotNeedToUpdate() throws Exception {
        this.task.setBinlogFile("xxxx.0010");
        this.task.setBinlogPos(10l);

        doNothing().when(target).saveTask();

        dumpWorker.checkAndUpdateBinlogInfo("xxxx.0010", 11l);
        verify(target, times(0)).saveTask();

        dumpWorker.checkAndUpdateBinlogInfo("xxxx.0011", 1l);
        verify(target, times(0)).saveTask();
    }

    @Test
    public void testBinlogNeedToUpdate() throws Exception {
        this.task.setBinlogFile("xxxx.0010");
        this.task.setBinlogPos(10l);

        doNothing().when(target).saveTask();

        dumpWorker.checkAndUpdateBinlogInfo("xxxx.0010", 9l);
        verify(target, times(1)).saveTask();

        dumpWorker.checkAndUpdateBinlogInfo("xxxx.0009", 100l);
        verify(target, times(2)).saveTask();
    }

    private void deleteFile() {
        if (file.exists()) {
            file.delete();
        }
    }
}