package com.pack.objects;

public class Deck {
    private int length;
    private int breadth;
    private int height;
    private int lnthInch;
    private int breadthInch;

    public int getLnthInch() {
        return lnthInch;
    }

    public void setLnthInch(int lnthInch) {
        this.lnthInch = lnthInch;
    }

    public int getBreadthInch() {
        return breadthInch;
    }

    public void setBreadthInch(int breadthInch) {
        this.breadthInch = breadthInch;
    }

    public Deck(int length, int lengthIN, int breadth, int breadthIn, int height) {
        this.length = length;
        this.breadth = breadth;
        this.height = height;
        this.lnthInch = lengthIN;
        this.breadthInch = breadthIn;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getBreadth() {
        return breadth;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return length + "\n" + lnthInch + "\n" + breadth + "\n" +breadthInch + "\n" + height ;
    }
}
