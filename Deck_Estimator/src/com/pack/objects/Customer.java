package com.pack.objects;

public class Customer implements Person{
    String name;
    String phoneNumber;
    String address;

    public Customer(String n, String p, String a){
        this.name = n;
        this.phoneNumber = p;
        this.address = a;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
