package com.pack.objects;

public class Deck {
    private float length;
    private float breadth;
    private int height;

    public Deck(int length, int lengthIN, int breadth, int breadthIn, int height) {
        this.length = length + (float)lengthIN/12;
        this.breadth = breadth + (float)breadthIn/12;
        this.height = height;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getBreadth() {
        return breadth;
    }

    public void setBreadth(float breadth) {
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
        return length + "\n" + breadth + "\n" + height ;
    }
}
