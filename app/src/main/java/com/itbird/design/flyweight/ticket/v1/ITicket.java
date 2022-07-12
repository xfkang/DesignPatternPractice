package com.itbird.design.flyweight.ticket.v1;

import java.util.Random;

/**
 * Created by itbird on 2022/7/11
 */
public abstract class ITicket {
    float price;
    String start;
    String end;

    public float getPrice() {
        Random random = new Random(1000);
        return random.nextFloat();
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
