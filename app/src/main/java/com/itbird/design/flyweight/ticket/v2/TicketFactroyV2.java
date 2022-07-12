package com.itbird.design.flyweight.ticket.v2;

import android.util.Log;

import com.itbird.design.flyweight.ticket.v1.ITicket;
import com.itbird.design.flyweight.ticket.v1.Ticket;

import java.util.HashMap;

/**
 * 负责火车票对象的管理，对象池概念
 * Created by itbird on 2022/7/11
 */
public class TicketFactroyV2 {
    private static final String TAG = TicketFactroyV2.class.getSimpleName();

    /**
     * 以“start_end”字符串为key，以真正的ticket对象为value，map来存储对象
     */
    HashMap<String, ITicket> map = new HashMap<>();

    public ITicket getTicket(String start, String end) {
        String key = start + "_" + end;
        if (map.containsKey(key)) {
            Log.d(TAG, "Cache Ticket");
            return map.get(key);
        } else {
            Log.d(TAG, "NEW Ticket");
            ITicket ticket = new Ticket(start, end);
            map.put(key, ticket);
            return ticket;
        }
    }
}
