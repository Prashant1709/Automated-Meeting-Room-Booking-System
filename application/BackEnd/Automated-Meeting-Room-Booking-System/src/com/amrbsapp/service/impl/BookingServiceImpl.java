package com.amrbsapp.service.impl;

import com.amrbsapp.entity.Booking;
import com.amrbsapp.service.BookingService;

import java.time.LocalDate;
import java.util.List;

public class BookingServiceImpl implements BookingService {
    @Override
    public Booking getBookingById(int bookingId) {
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
    public void deleteBooking(int bookingId) {

    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) {
        return List.of();
    }

    @Override
    public List<Booking> getBookingsByRoomId(int roomId) {
        return List.of();
    }

    @Override
    public boolean checkBookingAvailability(int roomId, LocalDate bookingDate, int duration) {
        return false;
    }
}
