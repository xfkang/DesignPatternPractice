package com.itbird.design.iterator.usersystem;

import com.itbird.design.iterator.usersystem.iterator.Iterator;
import com.itbird.design.iterator.usersystem.iterator.ListIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itbird on 2022/7/7
 */
public class UserinfoSystemA implements BaseSystem {
    ListIterator<Userinfo> userinfoIterator;
    List<Userinfo> userinfos = new ArrayList<>();

    public UserinfoSystemA() {
        userinfos.add(new Userinfo("小名", 1));
        userinfos.add(new Userinfo("小我", 1));
        userinfos.add(new Userinfo("小的", 1));
        userinfos.add(new Userinfo("小啊", 1));
        userinfoIterator.setList(userinfos);
    }

    public List<Userinfo> getUserinfos() {
        return userinfos;
    }

    @Override
    public Iterator<Userinfo> getIterator() {
        return userinfoIterator;
    }
}
