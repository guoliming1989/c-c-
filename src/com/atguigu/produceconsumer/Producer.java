package com.atguigu.produceconsumer;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

//阻塞队列
class Producer implements Runnable {
    private final BlockingQueue sharedQueue;
    public Producer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }
    @Override
    public void run() {
            try {
                while(true){
                    Random r = new Random(10);
                    sharedQueue.put(r.nextInt());
                    System.out.println("Produced: " + r.nextInt());
                }
            } catch (InterruptedException ex) {
               // Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
