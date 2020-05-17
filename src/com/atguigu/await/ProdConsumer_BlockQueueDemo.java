package com.atguigu.await;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource {
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    public BlockingQueue<String> getBlockingQueue() {
        return blockingQueue;
    }
    private BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void producer() throws InterruptedException {
        String data = null;
        boolean resFlag;
        while (flag) {
            data = atomicInteger.incrementAndGet() + "";
            resFlag = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (resFlag) {
                System.out.println(Thread.currentThread().getName() + " 生产队列, " + data + " 插入队列成功");
            } else {
                System.out.println(Thread.currentThread().getName() + " 生产队列, " + data + " 插入队列失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println(Thread.currentThread().getName() + "flag = false, 生产动作结束");
    }
    //启动一个消费者列表一直消费
    public void consumer() throws InterruptedException {
        String result = null;
        while (flag) {
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (result == null || result.equalsIgnoreCase("")) {
                flag = false;
                System.out.println(Thread.currentThread().getName() + " 超过2秒没有取到，停止消费");
                return;
            }
            System.out.println(Thread.currentThread().getName() + " 消费队列：" + result);
        }
    }

    public void stop() {
        this.flag = false;
    }
}

public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            //开启定时任务去执行
            @Override
            public void run() {
                if(myResource.getBlockingQueue()==null){
                   //获取队列的值，每次从api接口中获取的大小值
                    System.out.println("ScheduledTask");
                   //调用api接口去获取队列的数据,会存在重复获取的情况。拿到多少消费多少

                }else{
                    //队列有值，进行处理然后返回，如果消费者线程已经启动则不用启动
                    if(new Thread(()->{},"consumer").isAlive()==false){
                        new Thread(() -> {
                            System.out.println("消费线程启动...");
                            try {
                                myResource.consumer();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }, "consumer").start();
                    }
                }
            }
        }, 1, 1, TimeUnit.SECONDS);

        new Thread(() -> {
            System.out.println("生产线程启动...");
            try {
                myResource.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "producer").start();
        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
        myResource.stop();
        System.out.println(Thread.currentThread().getName() + " main 线程终止生产-消费");
    }
}