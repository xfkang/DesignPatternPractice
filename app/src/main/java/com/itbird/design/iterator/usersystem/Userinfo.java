package com.itbird.design.iterator.usersystem;

/**
 * Created by itbird on 2022/7/7
 */
public class Userinfo {
    String name;
    int age;

    public Userinfo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
