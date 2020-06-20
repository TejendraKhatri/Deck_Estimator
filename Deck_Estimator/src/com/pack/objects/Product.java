package com.pack.objects;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Product {
    private SimpleStringProperty name;
    private SimpleIntegerProperty qty;
    private SimpleDoubleProperty unitPrice;
    private SimpleDoubleProperty subTotal;

    public Product(String name, int qty, double unitPrice) {
        this.name = new SimpleStringProperty(name);
        this.qty = new SimpleIntegerProperty(qty);
        this.unitPrice = new SimpleDoubleProperty(unitPrice);
        this.subTotal = new SimpleDoubleProperty(qty*unitPrice);
    }

    public String getName() { return name.get();}
    public int getQty() { return qty.get();}
    public double getUnitPrice() { return unitPrice.get();}
    public double getSubTotal() { return subTotal.get();}
    

    @Override
    public String toString() {
        return name.toString() +
                "," + qty.toString() +
                "," + unitPrice.toString() +
                "," + subTotal.toString();
    }
}
