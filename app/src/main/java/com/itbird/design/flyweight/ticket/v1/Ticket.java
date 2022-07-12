package com.itbird.design.flyweight.ticket.v1;

import android.util.Log;

/**
 * 火车票对象
 * Created by itbird on 2022/7/11
 */
public class Ticket extends ITicket {
    private static final String TAG = Ticket.class.getSimpleName();

    public Ticket(String start, String end) {
        this.start = start;
        this.end = end;
    }
}
