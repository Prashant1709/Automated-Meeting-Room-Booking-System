package com.amrbsapp.dao.impl;

import com.amrbsapp.dao.BookingDAO;
import com.amrbsapp.entity.Booking;
import com.amrbsapp.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingImpl implements BookingDAO {

    @Override
    public Booking getBookingByID(int bookingID, Connection conn) {
        String sql = "SELECT * FROM amrbsapp.bookings WHERE booking_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookingID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Booking(
                        rs.getInt("bookingID"),
                        rs.getString("roomID"),
                        rs.getString("userID"),
                        rs.getTimestamp("bookingDate").toLocalDateTime(),
                        rs.getInt("duration")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Booking> getAllBookings(Connection conn) {
        String sql = "SELECT * FROM amrbsapp.bookings";
        List<Booking> bookings = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("bookingID"),
                        rs.getString("roomID"),
                        rs.getString("userID"),
                        rs.getTimestamp("bookingDate").toLocalDateTime(),
                        rs.getInt("duration")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public boolean saveBooking(Booking booking, Connection conn) {
        String sql = "INSERT INTO amrbsapp.bookings (roomID, userID, bookingDate, duration) VALUES (?, ?, ?, ?)";
        int rowsAffected = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, booking.getRoom());
            ps.setString(2, booking.getBookedBy());
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(booking.getBookingDate()));
            ps.setInt(4, booking.getDuration());
            rowsAffected = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean updateBooking(Booking booking, Connection conn) {
        String sql = "UPDATE amrbsapp.bookings SET roomID = ?, userID = ?, bookingDate = ?, duration = ? WHERE bookingID = ?";
        int rowsAffected = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, booking.getRoom());
            ps.setString(2, booking.getBookedBy());
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(booking.getBookingDate()));
            ps.setInt(4, booking.getDuration());
            ps.setInt(5, booking.getBookingID());
            rowsAffected = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteBooking(int bookingID, Connection conn) {
        String sql = "DELETE FROM amrbsapp.bookings WHERE bookingID = ?";
        int rowsAffected = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, bookingID);
            rowsAffected = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected > 0;
    }

    @Override
    public List<Booking> getBookingsByUserID(int userID, Connection conn) {
        String sql = "SELECT * FROM amrbsapp.bookings WHERE userID = ?";
        List<Booking> bookings = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("bookingID"),
                        rs.getString("roomID"),
                        rs.getString("userID"),
                        rs.getTimestamp("bookingDate").toLocalDateTime(),
                        rs.getInt("duration")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public List<Booking> getBookingsByRoomID(int roomID, Connection conn) {
        String sql = "SELECT * FROM amrbsapp.bookings WHERE roomID = ?";
        List<Booking> bookings = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, roomID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("bookingID"),
                        rs.getString("roomID"),
                        rs.getString("userID"),
                        rs.getTimestamp("bookingDate").toLocalDateTime(),
                        rs.getInt("duration")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
