package com.atguigu.observe;

public class ObserverPatternDemo {
    public static void main(String arg[]){
        Subject subject = new Subject();
        new BinaryObserver(subject);
        subject.setState(16);
        subject.notifyAllObservers();

    }
}
