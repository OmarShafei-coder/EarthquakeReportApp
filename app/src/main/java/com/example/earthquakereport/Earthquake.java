package com.example.earthquakereport;

//The module class
public class Earthquake {
    private double magnitude;
    private String place;
    private String date;

    public Earthquake(double magnitude, String place, String date) {
        this.magnitude = magnitude;
        this.place = place;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
