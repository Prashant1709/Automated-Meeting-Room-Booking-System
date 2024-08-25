package com.amrbsapp.controller;

import com.amrbsapp.entity.Booking;
import com.amrbsapp.service.BookingService;
import com.amrbsapp.util.DBConnection;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class BookingController {

    private BookingService bookingService;
    private Connection conn= DBConnection.getConnection();
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public Booking getBooking(int bookingID){
        return this.bookingService.getBookingById(bookingID, conn);
    }
    public List<Booking> getAllBookings(){
        return this.bookingService.getAllBookings(conn);
    }
    public boolean addBooking(Booking booking){
        return this.bookingService.saveBooking(booking, conn);
    }
    public boolean updateBooking(Booking booking){
        return this.bookingService.updateBooking(booking, conn);
    }
    public boolean deleteBooking(int bookingID){
        return this.bookingService.deleteBooking(bookingID, conn);
    }
    public List<Booking> getBookingsByUserId(int userID){
        return this.bookingService.getBookingsByUserId(userID, conn);
    }
    public List<Booking> getBookingsByRoomId(int roomID){
        return this.bookingService.getBookingsByRoomId(roomID, conn);
    }
}
