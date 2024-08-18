package com.amrbsapp.dao.impl;

import com.amrbsapp.dao.BookingDAO;
import com.amrbsapp.entity.Booking;
import com.amrbsapp.entity.Room;

import java.util.List;

public class BookingImpl implements BookingDAO {


    @Override
    public Booking getBookingByID(int bookingID) {
        return null;
    }

    @Override
    public List<Booking> getAllBookings() {
        return List.of();
    }

    @Override
    public void saveBooking(Booking booking) {

    }

    @Override
    public void updateBooking(Booking booking) {

    }

    @Override
    public void deleteBooking(int bookingID) {

    }

    @Override
    public List<Booking> getBookingsByUserID(Room room) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingsByRoomID(int roomID) {
        return List.of();
    }
}
