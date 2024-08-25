package com.amrbsapp.service;

import com.amrbsapp.entity.Booking;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    Booking getBookingById(int bookingId, Connection conn);
    List<Booking> getAllBookings(Connection conn);
    boolean saveBooking(Booking booking, Connection conn);
    boolean updateBooking(Booking booking, Connection conn);
    boolean deleteBooking(int bookingId, Connection conn);
    List<Booking> getBookingsByUserId(int userId, Connection conn);
    List<Booking> getBookingsByRoomId(int roomId, Connection conn);

}
