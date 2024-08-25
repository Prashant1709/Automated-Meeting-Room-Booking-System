package com.amrbsapp.entity;

import java.util.List;

public class Room {
    private int roomID;
    private int capacity;
    private List<Amenity> mandatoryAmenities;
    private boolean isBooked;

    public Room(int roomID, int capacity, List<Amenity> mandatoryAmenities, boolean isBooked) {
        this.roomID = roomID;
        this.capacity = capacity;
        this.mandatoryAmenities = mandatoryAmenities;
        this.isBooked = isBooked;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Amenity> getMandatoryAmenities() {
        return mandatoryAmenities;
    }

    public void setMandatoryAmenities(List<Amenity> mandatoryAmenities) {
        this.mandatoryAmenities = mandatoryAmenities;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID='" + roomID + '\'' +
                ", capacity=" + capacity +
                ", mandatoryAmenities=" + mandatoryAmenities +
                ", isBooked=" + isBooked +
                '}';
    }
    public String toJson() {
        return "{" +
                "\"roomID\":\"" + roomID + '\"' +
                ", \"capacity\":" + capacity +
                ", \"mandatoryAmenities\":" + mandatoryAmenities +
                ", \"isBooked\":" + isBooked +
                '}';
    }
}