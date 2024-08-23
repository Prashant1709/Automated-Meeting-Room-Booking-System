package com.amrbsapp.dao;

import com.amrbsapp.entity.Booking;
import com.amrbsapp.entity.Room;

import java.sql.Connection;
import java.util.List;

public interface BookingDAO {

    Booking getBookingByID(int bookingID, Connection conn);
    List<Booking> getAllBookings(Connection conn);
    boolean saveBooking(Booking booking, Connection conn);
    boolean updateBooking(Booking booking, Connection conn);
    boolean deleteBooking(int bookingID, Connection conn);
    List<Booking> getBookingsByUserID(int userID, Connection conn);
    List<Booking> getBookingsByRoomID(int roomID, Connection conn);

}
