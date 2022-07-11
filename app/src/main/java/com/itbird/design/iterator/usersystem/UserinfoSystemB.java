package com.itbird.design.iterator.usersystem;

import com.itbird.design.iterator.usersystem.iterator.ArrayIterator;
import com.itbird.design.iterator.usersystem.iterator.Iterator;

/**
 * Created by itbird on 2022/7/7
 */
public class UserinfoSystemB implements BaseSystem {
    ArrayIterator<Userinfo> userinfoIterator;
    Userinfo[] userinfos = new Userinfo[3];

    public UserinfoSystemB() {
        userinfos[0] = new Userinfo("小去", 1);
        userinfos[1] = new Userinfo("小我", 1);
        userinfos[2] = new Userinfo("小打", 1);
        userinfoIterator.setList(userinfos);
    }

    public Userinfo[] getUserinfos() {
        return userinfos;
    }

    @Override
    public Iterator<Userinfo> getIterator() {
        return userinfoIterator;
    }
}
