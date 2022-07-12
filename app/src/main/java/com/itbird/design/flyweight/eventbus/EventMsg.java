package com.itbird.design.flyweight.eventbus;

/**
 * Created by itbird on 2022/3/2
 */
public class EventMsg {
    public EventMsg(String msg) {
        messge = msg;
    }

    public String getMessge() {
        return messge;
    }

    public void setMessge(String messge) {
        this.messge = messge;
    }

    String messge;

}
