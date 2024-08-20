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

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(User bookedBy) {
        this.bookedBy = bookedBy;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID='" + bookingID + '\'' +
                ", room=" + room +
                ", bookedBy=" + bookedBy +
                ", bookingDate=" + bookingDate +
                ", duration=" + duration +
                '}';
    }
}