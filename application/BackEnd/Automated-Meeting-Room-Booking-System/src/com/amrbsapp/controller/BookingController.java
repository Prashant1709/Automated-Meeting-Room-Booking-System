package com.amrbsapp.controller;

import com.amrbsapp.entity.Booking;
import com.amrbsapp.service.BookingService;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class BookingController {

    private BookingService bookingService;
    private Connection conn;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    Booking getBooking(int bookingID, Connection conn){
        return this.bookingService.getBookingById(bookingID, conn);
    }
    List<Booking> getAllBookings(Connection conn){
        return this.bookingService.getAllBookings(conn);
    }
    boolean addBooking(Booking booking, Connection conn){
        return this.bookingService.saveBooking(booking, conn);
    }
    boolean updateBooking(Booking booking, Connection conn){
        return this.bookingService.updateBooking(booking, conn);
    }
    boolean deleteBooking(int bookingID, Connection conn){
        return this.bookingService.deleteBooking(bookingID, conn);
    }
    List<Booking> getBookingsByUserId(int userID, Connection conn){
        return this.bookingService.getBookingsByUserId(userID, conn);
    }
    List<Booking> getBookingsByRoomId(int roomID, Connection conn){
        return this.bookingService.getBookingsByRoomId(roomID, conn);
    }
}
