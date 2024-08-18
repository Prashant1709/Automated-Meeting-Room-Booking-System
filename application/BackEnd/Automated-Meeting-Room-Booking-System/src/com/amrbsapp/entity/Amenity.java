package com.amrbsapp.entity;

public class Amenity {
    private String amenityID;
    private String name;
    private int creditCost;

    public Amenity(String amenityID, String name, int creditCost) {
        this.amenityID = amenityID;
        this.name = name;
        this.creditCost = creditCost;
    }

    // Display Amenity Details
    public void displayAmenityDetails() {
        System.out.println("Amenity ID: " + amenityID);
        System.out.println("Name: " + name);
        System.out.println("Credit Cost: " + creditCost);
    }

    // Getters and Setters
    public String getAmenityID() { return amenityID; }
    public String getName() { return name; }
    public int getCreditCost() { return creditCost; }
}

