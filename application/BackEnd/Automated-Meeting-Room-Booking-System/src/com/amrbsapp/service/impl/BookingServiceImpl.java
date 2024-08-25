package com.amrbsapp.service.impl;

import com.amrbsapp.dao.BookingDAO;
import com.amrbsapp.entity.Booking;
import com.amrbsapp.service.BookingService;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class BookingServiceImpl implements BookingService {

    private BookingDAO bookingDAO;

    public BookingServiceImpl(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    @Override
    public Booking getBookingById(int bookingId, Connection conn) {
        return this.bookingDAO.getBookingByID(bookingId, conn);
    }

    @Override
    public List<Booking> getAllBookings(Connection conn) {
        return this.bookingDAO.getAllBookings(conn);
    }

    @Override
    public boolean saveBooking(Booking booking, Connection conn) {
        return this.bookingDAO.saveBooking(booking, conn);
    }

    @Override
    public boolean updateBooking(Booking booking, Connection conn) {
        return this.bookingDAO.updateBooking(booking, conn);
    }

    @Override
    public boolean deleteBooking(int bookingId, Connection conn) {
        return this.bookingDAO.deleteBooking(bookingId, conn);
    }

    @Override
    public List<Booking> getBookingsByUserId(int userId, Connection conn) {
        return this.bookingDAO.getBookingsByUserID(userId, conn);
    }

    @Override
    public List<Booking> getBookingsByRoomId(int roomId, Connection conn) {
        return this.bookingDAO.getBookingsByRoomID(roomId, conn);
    }
}
