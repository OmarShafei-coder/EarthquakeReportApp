package com.example.earthquakereport;

//The module class
public class Earthquake {
    private double magnitude;
    private String primaryLocation;
    private String locationOffset;
    private String date;
    private String url;

    public Earthquake(double magnitude, String primaryLocation, String locationOffset, String date, String url) {
        this.magnitude = magnitude;
        this.primaryLocation = primaryLocation;
        this.locationOffset = locationOffset;
        this.date = date;
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getPrimaryLocation() {
        return primaryLocation;
    }

    public String getLocationOffset() {
        return locationOffset;
    }

    public String getDate() {
        return date;
    }

    public String getUrl() {
        return url;
    }
}