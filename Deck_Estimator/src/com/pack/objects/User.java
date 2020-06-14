package com.pack.objects;

import com.pack.interfaces.Person;

public class User implements Person {
    private String name;
    private String phoneNumber;

    public User(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPhoneNum() {
        return this.phoneNumber;
    }

    @Override
    public void setPhoneNum(String number) {
        this.phoneNumber = number;
    }
}
