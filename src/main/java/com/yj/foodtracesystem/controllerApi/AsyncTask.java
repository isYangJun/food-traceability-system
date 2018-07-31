package com.yj.foodtracesystem.controllerApi;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 7:02 2018/7/31
 */
@Component
public class AsyncTask {
    @Async
    public Future<Boolean> doTask11()throws Exception{
        long start =System.currentTimeMillis();
        Thread.sleep(1000);
        long end =System.currentTimeMillis();
        System.out.println("任務1 耗時："+(end-start)+" ms");
        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> doTask22()throws Exception{
        long start =System.currentTimeMillis();
        Thread.sleep(700);
        long end =System.currentTimeMillis();
        System.out.println("任務2 耗時："+(end-start)+" ms");
        return new AsyncResult<>(true);
    }
    @Async
    public Future<Boolean> doTask33()throws Exception{
        long start =System.currentTimeMillis();
        Thread.sleep(600);
        long end =System.currentTimeMillis();
        System.out.println("任務3 耗時："+(end-start)+" ms");
        return new AsyncResult<>(true);
    }
}
