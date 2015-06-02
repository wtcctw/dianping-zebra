package com.dianping.zebra.admin.job.executor;

import com.dianping.zebra.admin.entity.ShardDumpDbEntity;
import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */

public class ShardDumpTaskExecutorMain {
    public static void main(String... args) throws InterruptedException {
        ShardDumpTaskEntity task = new ShardDumpTaskEntity();

        task.setIndexIncrease(1000000);
        task.setDataBase("test");
        task.setTableName("user");
        task.setIndexColumnName("id");
        task.setIndexKey(0);
        task.setMaxKey(10000000);
        task.setName("debug");
        task.setShardRule("id % 100 <> 0");
        task.setTargetTableName("user_0");
        task.setTargetDataBase("test1");

        ShardDumpDbEntity src = new ShardDumpDbEntity();
        src.setHost("127.0.0.1");
        src.setPort(3306);
        src.setUsername("root");
        src.setPassword("root");

        ShardDumpDbEntity dst = new ShardDumpDbEntity();
        dst.setHost("127.0.0.1");
        dst.setPort(3306);
        dst.setUsername("root");
        dst.setPassword("root");

        task.setSrcDbEntity(src);
        task.setDstDbEntity(dst);

        ShardDumpTaskExecutor target = new ShardDumpTaskExecutor(task);

        //        ShardDumpTaskService service = mock(ShardDumpTaskService.class);

        //        doAnswer(new Answer() {
        //            @Override
        //            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
        //                ShardDumpTask temp = (ShardDumpTask) invocationOnMock.getArguments()[0];
        //                System.out.println(temp.getBinlogInfo().getBinlogFile());
        //                System.out.println(temp.getBinlogInfo().getBinlogPosition());
        //                return null;
        //            }
        //        }).when(service).update(any(ShardDumpTask.class));

        //        target.setShardDumpTaskService(service);
        target.init();
        target.start();

        /*
        while (target.getTaskState().getPercent() < 100) {
            System.out.println(target.getTaskState().getStatus());
            System.out.println(target.getTaskState().getPercent());
            Thread.sleep(1000);
        }

        System.out.println(target.getTaskState().getStatus());
        System.out.println(target.getTaskState().getPercent());*/
    }
}
