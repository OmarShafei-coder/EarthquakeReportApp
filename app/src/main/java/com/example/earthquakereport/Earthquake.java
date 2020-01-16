package com.example.earthquakereport;

//The module class
public class Earthquake {
    private double magnitude;
    private String place;
    private long time;

    public Earthquake(double magnitude, String place, long time) {
        this.magnitude = magnitude;
        this.place = place;
        this.time = time;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
