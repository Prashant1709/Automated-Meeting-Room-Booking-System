package com.amrbsapp.entity;

import java.time.LocalDateTime;

public class Booking {
    private int bookingID;
    private String room;
    private String bookedBy;
    private LocalDateTime bookingDate;
    private int duration;

    public Booking(int bookingID, String room, String bookedBy, LocalDateTime bookingDate, int duration) {
        this.bookingID = bookingID;
        this.room = room;
        this.bookedBy = bookedBy;
        this.bookingDate = bookingDate;
        this.duration = duration;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
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