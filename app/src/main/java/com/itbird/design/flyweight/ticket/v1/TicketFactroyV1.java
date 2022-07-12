package com.itbird.design.flyweight.ticket.v1;

import android.util.Log;

/**
 * 负责火车票对象的管理
 * Created by itbird on 2022/7/11
 */
public class TicketFactroyV1 {
    private static final String TAG = TicketFactroyV1.class.getSimpleName();

    public ITicket getTicket(String start, String end) {
        Log.d(TAG, "NEW Ticket");
        return new Ticket(start, end);
    }
}
