package com.amrbsapp.controller;

import com.amrbsapp.entity.Booking;

import java.time.LocalDate;
import java.util.List;

public class BookingController {

    Booking getBooking(int bookingID){
        return null;
    }
    List<Booking> getAllBookings(){
        return null;
    }
    void addBooking(Booking booking){

    }
    void updateBooking(Booking booking){

    }
    void deleteBooking(int bookingID){

    }
    List<Booking> getBookingsByUserId(int userID){
        return null;
    }
    List<Booking> getBookingsByRoomId(int roomID){
        return null;
    }
    boolean checkAvailability(int roomID, LocalDate bookingDate, int duration){
        return false;
    }
}
