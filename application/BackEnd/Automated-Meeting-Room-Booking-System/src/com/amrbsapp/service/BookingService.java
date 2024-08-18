package com.amrbsapp.service;

import com.amrbsapp.entity.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    Booking getBookingById(int bookingId);
    List<Booking> getAllBookings();
    void saveBooking(Booking booking);
    void updateBooking(Booking booking);
    void deleteBooking(int bookingId);
    List<Booking> getBookingsByUserId(int userId);
    List<Booking> getBookingsByRoomId(int roomId);
    boolean checkBookingAvailability(int roomId, LocalDate bookingDate,int duration);

}
