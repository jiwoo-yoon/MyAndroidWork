package com.lec.android.a008_practice;

import java.io.Serializable;

public class Info implements Serializable {
    String name;
    String age;
    String addr;

    public Info() {
    }

    public Info(String name, String age, String addr) {
        this.name = name;
        this.age = age;
        this.addr = addr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
