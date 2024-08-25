package test.com.amrbsapp;


import com.amrbsapp.dao.impl.AmenityImpl;
import com.amrbsapp.dao.impl.BookingImpl;
import com.amrbsapp.entity.Amenity;
import com.amrbsapp.entity.Booking;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

@DisplayName("AMRBS Booking App Tests")
public class BookingDAOTests {

    private static Connection connection;
    private BookingImpl bookingDAO;

    @BeforeEach
    public void setUpBeforeClass() throws Exception {
        // Set up the database connection
        String url = "jdbc:mysql://localhost:3306/amrbsapp_test"; // Use your test DB
        String username = "root";
        String password = "password"; // Use the appropriate password
        connection = DriverManager.getConnection(url, username, password);
    }

    @BeforeEach
    public void setUp() {
        bookingDAO = new BookingImpl();
    }

    @Test
    public void testSaveBooking() throws Exception {
        Booking booking = new Booking(0, "R101", "U123", LocalDateTime.now(), 2); // BookingID is set to 0

        boolean isSaved = bookingDAO.saveBooking(booking, connection);
        assertTrue(isSaved, "Booking should be saved successfully");

        List<Booking> bookings = bookingDAO.getAllBookings(connection);
        boolean found = false;
        for (Booking b : bookings) {
            if (b.getRoom().equals("R101") && b.getBookedBy().equals("U123")) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Booking should be found in the database");
    }

    @Test
    public void testGetBookingByID() throws Exception {
        // Assuming a booking with ID 1 exists in your test database
        Booking booking = bookingDAO.getBookingByID(1, connection);

        assertNotNull(booking);
        assertEquals(1, booking.getBookingID());
        assertEquals("R101", booking.getRoom()); // Replace with actual data from your DB
        assertEquals("U123", booking.getBookedBy()); // Replace with actual data from your DB
    }

    @Test
    public void testUpdateBooking() throws Exception {
        // Assuming a booking with ID 1 exists
        Booking booking = new Booking(1, "R102", "U124", LocalDateTime.now().plusDays(1), 3);

        boolean isUpdated = bookingDAO.updateBooking(booking, connection);
        assertTrue(isUpdated, "Booking should be updated successfully");

        Booking updatedBooking = bookingDAO.getBookingByID(1, connection);
        assertEquals("R102", updatedBooking.getRoom());
        assertEquals("U124", updatedBooking.getBookedBy());
        assertEquals(3, updatedBooking.getDuration());
    }

    @Test
    public void testDeleteBooking() throws Exception {
        // Assuming a booking with ID 2 exists
        boolean isDeleted = bookingDAO.deleteBooking(2, connection);
        assertTrue(isDeleted, "Booking should be deleted successfully");

        Booking deletedBooking = bookingDAO.getBookingByID(2, connection);
        assertNull(deletedBooking);
    }

    @Test
    public void testGetAllBookings() throws Exception {
        // Insert multiple bookings to test retrieval
        Booking booking1 = new Booking(0, "R103", "U125", LocalDateTime.now().plusDays(2), 1);
        Booking booking2 = new Booking(0, "R104", "U126", LocalDateTime.now().plusDays(3), 2);
        bookingDAO.saveBooking(booking1, connection);
        bookingDAO.saveBooking(booking2, connection);

        List<Booking> bookings = bookingDAO.getAllBookings(connection);

        assertTrue(bookings.size() >= 2, "Should retrieve at least 2 bookings");
    }

    @Test
    public void testGetBookingsByUserID() throws Exception {
        // Assuming a booking with userID U123 exists
        List<Booking> bookings = bookingDAO.getBookingsByUserID(123, connection);
        assertNotNull(bookings);
        assertTrue(!bookings.isEmpty(), "Bookings list should not be empty");

        for (Booking booking : bookings) {
            assertEquals("U123", booking.getBookedBy());
        }
    }

    @Test
    public void testGetBookingsByRoomID() throws Exception {
        // Assuming a booking with roomID R101 exists
        List<Booking> bookings = bookingDAO.getBookingsByRoomID(101, connection);
        assertNotNull(bookings);
        assertTrue(!bookings.isEmpty(), "Bookings list should not be empty");

        for (Booking booking : bookings) {
            assertEquals("R101", booking.getRoom());
        }
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
