package com.example.earthquakereport;

//The module class
public class Earthquake {
    private double magnitude;
    private String primaryLocation;
    private String locationOffset;
    private String date;

    public Earthquake(double magnitude, String primaryLocation, String locationOffset, String date) {
        this.magnitude = magnitude;
        this.primaryLocation = primaryLocation;
        this.locationOffset = locationOffset;
        this.date = date;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPrimaryLocation() {
        return primaryLocation;
    }

    public void setPrimaryLocation(String primaryLocation) {
        this.primaryLocation = primaryLocation;
    }

    public String getLocationOffset() {
        return locationOffset;
    }

    public void setLocationOffset(String locationOffset) {
        this.locationOffset = locationOffset;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}