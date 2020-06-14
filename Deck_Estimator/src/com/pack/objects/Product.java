package com.pack.objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Product {
    private StringProperty name;
    private IntegerProperty id;
    private IntegerProperty qty;
    private IntegerProperty unitPrice;

    public Product(StringProperty name, IntegerProperty id, IntegerProperty qty, IntegerProperty unitPrice) {
        this.name = name;
        this.id = id;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getQty() {
        return qty.get();
    }

    public IntegerProperty qtyProperty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty.set(qty);
    }

    public int getUnitPrice() {
        return unitPrice.get();
    }

    public IntegerProperty unitPriceProperty() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice.set(unitPrice);
    }

    @Override
    public String toString() {
        return name +
                "," + id +
                "," + qty +
                "," + unitPrice;
    }
}
