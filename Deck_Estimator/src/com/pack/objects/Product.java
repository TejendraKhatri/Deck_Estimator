package com.pack.objects;

import javafx.beans.property.StringProperty;

public class Product {
    String name;
    int id;
    int qty;
    int unitPrice;

    public Product(String name, int id, int qty, int unitPrice) {
        this.name = name;
        this.id = id;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return name + ',' + id +"," + qty +
                "," +  unitPrice;
    }
}
