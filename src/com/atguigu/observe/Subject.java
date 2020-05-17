package com.atguigu.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * 实体对象获取添加观察者，然后通知观察者更改信息，观察者获得实体对象。
 */
public class Subject {
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public void attach(Observer observer){
        observers.add(observer);
    }
    private int state;
    private List<Observer> observers = new ArrayList<Observer>();

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
