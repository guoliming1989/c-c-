package com.atguigu.await;

public class LineUpEvent implements IEvent {
    private IListener listener;
    @Override
    public void setIListener(IListener listener) {
        this.listener = listener;
    }
    public void eventHappened() {
        listener.doEvent(this);
    }
}
