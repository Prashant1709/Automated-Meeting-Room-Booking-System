package com.amrbsapp.dao;

import com.amrbsapp.entity.Booking;
import com.amrbsapp.entity.Room;

import java.util.List;

public interface BookingDAO {

    Booking getBookingByID(int bookingID);
    List<Booking> getAllBookings();
    void saveBooking(Booking booking);
    void updateBooking(Booking booking);
    void deleteBooking(int bookingID);
    List<Booking> getBookingsByUserID(Room room);
    List<Booking> getBookingsByRoomID(int roomID);

}
