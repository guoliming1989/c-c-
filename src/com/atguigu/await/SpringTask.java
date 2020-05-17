package com.atguigu.await;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//开启定时任务去执行
public class SpringTask {
    public  static  void main(String agrs[]){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("ScheduledTask");
            }
        }, 1, 1, TimeUnit.SECONDS);
    }
}
