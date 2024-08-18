package com.amrbsapp.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Manager extends User {
    private int credits;

    public Manager(String userID, String name, String email, String phone) {
        super(userID, name, email, phone, "Manager");
        this.credits = 2000; // Initial credits
    }

    // Book Room method
    public Booking bookRoom(Room room, LocalDateTime bookingDate, int duration)  {
        int cost = room.calculateCostPerHour() * duration;
        deductCredits(cost);

        Booking booking = new Booking("B" + System.currentTimeMillis(), room, this, bookingDate, duration);
        booking.confirmBooking();
        System.out.println("Room " + room.getRoomID() + " booked successfully.");
        return booking;
    }

    // Organize Meeting
    public Meeting organizeMeeting(Room room, List<User> participants, LocalDateTime meetingDate, String meetingType, int duration) {
        Meeting meeting = new Meeting("M" + System.currentTimeMillis(), room, participants, meetingDate, meetingType, duration);
        meeting.checkMandatoryAmenities();
        System.out.println("Meeting organized successfully.");
        return meeting;
    }

    // View Bookings
    public void viewBookings(List<Booking> bookings) {
        System.out.println("Viewing all bookings for manager: " + this.getName());
        for (Booking booking : bookings) {
            if (booking.getBookedBy().getUserID().equals(this.getUserID())) {
                System.out.println("Booking ID: " + booking.getBookingID() + ", Room: " + booking.getRoom().getRoomID() + ", Date: " + booking.getBookingDate());
            }
        }
    }

    public void deductCredits(int amount) {
        if (this.credits < amount) {
//            throw new InsufficientCreditsException("Not enough credits to book the room.");
        }
        this.credits -= amount;
    }

    // Reset credits to 2000 every Monday
    public void resetCredits() {
        this.credits = 2000;
    }

    public int getCredits() {
        return credits;
    }
}

