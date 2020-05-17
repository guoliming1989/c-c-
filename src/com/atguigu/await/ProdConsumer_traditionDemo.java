package com.atguigu.await;

public class ProdConsumer_traditionDemo {
    public static void main(String[] args){
        ShareData shareData = new ShareData();
        new Thread(()->{
            shareData.increment();
        },"threadA").start();
        new Thread(()->{
            shareData.decrement();
        },"threadB").start();
    }
}
