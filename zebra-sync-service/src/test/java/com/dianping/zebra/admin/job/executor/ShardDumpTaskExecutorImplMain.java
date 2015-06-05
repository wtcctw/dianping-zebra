package com.dianping.zebra.admin.job.executor;

import com.dianping.zebra.admin.entity.ShardDumpDbEntity;
import com.dianping.zebra.admin.entity.ShardDumpTaskEntity;
import com.dianping.zebra.admin.service.ShardDumpService;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

/**
 * Dozer @ 2015-02
 * mail@dozer.cc
 * http://www.dozer.cc
 */

public class ShardDumpTaskExecutorImplMain {
    public static void main(String... args) throws InterruptedException {
        ShardDumpTaskEntity task = new ShardDumpTaskEntity();

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

        ShardDumpService service = mock(ShardDumpService.class);

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                ShardDumpTaskEntity temp = (ShardDumpTaskEntity) invocationOnMock.getArguments()[0];
                System.out.println(temp.getBinlogFile());
                System.out.println(temp.getBinlogPos());
                System.out.println(temp.getStatus());
                return null;
            }
        }).when(service).updateTaskStatus(any(ShardDumpTaskEntity.class));

        target.setShardDumpService(service);
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
