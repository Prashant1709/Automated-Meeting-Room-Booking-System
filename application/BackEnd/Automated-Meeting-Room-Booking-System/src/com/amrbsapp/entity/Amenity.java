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

    public String getAmenityID() {
        return amenityID;
    }

    public void setAmenityID(String amenityID) {
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
                "amenityID='" + amenityID + '\'' +
                ", name='" + name + '\'' +
                ", creditCost=" + creditCost +
                '}';
    }
}