package com.amrbsapp.entity;

import java.util.List;

public class Room {
    private String roomID;
    private int capacity;
    private List<Amenity> mandatoryAmenities;
    private boolean isBooked;

    public Room(String roomID, int capacity, List<Amenity> mandatoryAmenities) {
        this.roomID = roomID;
        this.capacity = capacity;
        this.mandatoryAmenities = mandatoryAmenities;
        this.isBooked = false;
    }

    // Display Room Details method
    public void displayRoomDetails() {
        System.out.println("Room ID: " + roomID);
        System.out.println("Capacity: " + capacity);
        System.out.println("Amenities: ");
        for (Amenity amenity : mandatoryAmenities) {
            amenity.displayAmenityDetails();
        }
        System.out.println("Is Booked: " + (isBooked ? "Yes" : "No"));
    }

    // Calculate the per hour cost based on amenities
    public int calculateCostPerHour() {
        int cost = 0;

        if (capacity <= 5) {
            cost += 0;
        } else if (capacity > 5 && capacity < 10) {
            cost += 10;
        } else {
            cost += 20;
        }

        for (Amenity amenity : mandatoryAmenities) {
            cost += amenity.getCreditCost();
        }

        return cost;
    }

    // Check if room can be booked
    public boolean canBeBooked() {
        return !this.isBooked;
    }

    // Mark the room as booked
    public void bookRoom() {
        this.isBooked = true;
    }

    // Getters and Setters
    public String getRoomID() { return roomID; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public List<Amenity> getMandatoryAmenities() { return mandatoryAmenities; }
    public void setMandatoryAmenities(List<Amenity> mandatoryAmenities) { this.mandatoryAmenities = mandatoryAmenities; }
    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}

