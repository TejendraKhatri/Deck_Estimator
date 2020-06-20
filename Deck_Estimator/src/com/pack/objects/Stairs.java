package com.pack.objects;

public class Stairs {
    private float totalRise;
    private float totalRun;

    public Stairs(float totalRise, float totalRun) {
        this.totalRise = totalRise;
        this.totalRun = totalRun;
    }

    public float getTotalRise() {
        return totalRise;
    }

    public void setTotalRise(float totalRise) {
        this.totalRise = totalRise;
    }

    public float getTotalRun() {
        return totalRun;
    }

    public void setTotalRun(float totalRun) {
        this.totalRun = totalRun;
    }

    @Override
    public String toString() {
        return "totalRise=" + totalRise +
                ", totalRun=" + totalRun;
    }
}
