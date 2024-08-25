package com.amrbsapp.entity;

public class Amenity {
    private int amenityID;
    private String name;
    private int creditCost;

    public Amenity(int amenityID, String name, int creditCost) {
        this.amenityID = amenityID;
        this.name = name;
        this.creditCost = creditCost;
    }

    public int getAmenityID() {
        return amenityID;
    }

    public void setAmenityID(int amenityID) {
        this.amenityID = amenityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditCost() {
        return creditCost;
    }

    public void setCreditCost(int creditCost) {
        this.creditCost = creditCost;
    }

    @Override
    public String toString() {
        return "Amenity{" +
                "amenityID=" + amenityID +
                ", name='" + name + '\'' +
                ", creditCost=" + creditCost +
                '}';
    }
}