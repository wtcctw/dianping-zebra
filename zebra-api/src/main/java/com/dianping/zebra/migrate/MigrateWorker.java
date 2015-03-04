package com.dianping.zebra.migrate;

import com.dianping.zebra.migrate.sql.SqlBuilder;
import com.dianping.zebra.migrate.task.TaskConfig;
import com.dianping.zebra.migrate.task.TaskLoader;
import com.dianping.zebra.shard.jdbc.ShardDataSource;
import com.dianping.zebra.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */
public class MigrateWorker {
    private ShardDataSource shardDataSource;

    private TaskLoader taskLoader;

    private Thread workerThread;

    public void init() {
        if (shardDataSource == null) {
            throw new NullPointerException("ShardDataSource");
        }

        if (shardDataSource.getOriginDataSource() == null) {
            throw new NullPointerException("ShardDataSource.OriginDataSource");
        }

        this.taskLoader = new TaskLoader();

        this.workerThread = new Thread(new MigrateWorkerRunner());
        this.workerThread.setDaemon(true);
        this.workerThread.setName("Dal-Migrate-Worker");
        this.workerThread.start();
    }

    public void close() {
        this.workerThread.interrupt();
    }

    public void setShardDataSource(ShardDataSource shardDataSource) {
        this.shardDataSource = shardDataSource;
    }

    class MigrateWorkerRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                TaskConfig task = taskLoader.loadConfig();

                if (TaskConfig.TASK_TYPE_MIGRATE.equals(task.getTaskType())) {
                    processMigrate(task);
                } else {

                }
            }
        }

        private void processMigrate(TaskConfig task) {
            String selectSql = SqlBuilder.getSelect(task);
            int pageIndex = 0;
            while (task.getPageSize() * pageIndex < task.getKeyEnd() - task.getKeyStart()) {
                Connection sConn = null;
                PreparedStatement sPrep = null;
                ResultSet resultSet = null;

                try {
                    sConn = shardDataSource.getConnection(false);
                    sPrep = sConn.prepareStatement(selectSql);
                    sPrep.setInt(1, task.getKeyStart());
                    sPrep.setInt(2, task.getKeyEnd());
                    sPrep.setInt(3, task.getPageSize() * pageIndex++);
                    sPrep.setInt(4, task.getPageSize());
                    resultSet = sPrep.executeQuery();

                    String insertSql = SqlBuilder.getInsert(task, resultSet);
                    int columnCount = resultSet.getMetaData().getColumnCount();

                    while (resultSet.next()) {
                        Connection iConn = null;
                        PreparedStatement iPrep = null;

                        try {
                            iConn = shardDataSource.getConnection(true);
                            iPrep = iConn.prepareStatement(insertSql);
                            for (int k = 1; k <= columnCount; k++) {
                                iPrep.setObject(k, resultSet.getObject(k));
                            }
                            iPrep.executeUpdate();
                        } finally {
                            JDBCUtils.closeAll(iPrep, iConn);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    JDBCUtils.closeAll(resultSet, sPrep, sConn);
                }
            }
        }
    }
}
