package com.atguigu.await;
import java.util.LinkedList;
public class LinkListener {
    private static int SIZE = 0;
    public static void main(String[] args){
        LinkedList<Object> queue = new LinkedList<>();
        IListener listener = new IListener(){
            @Override
            public void doEvent(IEvent event) {
                System.out.println("执行前队列大小:" + SIZE);
                System.out.println("监听器触发...");
                SIZE++;
                System.out.println("执行结束。。。队列大小：" + SIZE);
                System.out.println("-----------");
            }
        };
        LineUpEvent event = new LineUpEvent();
        for (int i = 0; i < 5; i++) {
            queue.offer(new Object());
            event.eventHappened();
        }
    }
}
