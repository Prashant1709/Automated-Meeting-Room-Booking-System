package com.amrbsapp.entity;

import java.time.LocalDateTime;

public class Booking {
    private String bookingID;
    private Room room;
    private User bookedBy;
    private LocalDateTime bookingDate;
    private int duration;

    public Booking(String bookingID, Room room, User bookedBy, LocalDateTime bookingDate, int duration) {
        this.bookingID = bookingID;
        this.room = room;
        this.bookedBy = bookedBy;
        this.bookingDate = bookingDate;
        this.duration = duration;
    }

    // Confirm Booking method
    public void confirmBooking() {
        room.bookRoom();
        System.out.println("Booking confirmed: " + bookingID);
    }

    // Calculate total cost for the booking (from previous code)
    public int calculateTotalCost() {
        return room.calculateCostPerHour() * duration;
    }

    // Getters and Setters
    public String getBookingID() { return bookingID; }
    public Room getRoom() { return room; }
    public User getBookedBy() { return bookedBy; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public int getDuration() { return duration; }
}

