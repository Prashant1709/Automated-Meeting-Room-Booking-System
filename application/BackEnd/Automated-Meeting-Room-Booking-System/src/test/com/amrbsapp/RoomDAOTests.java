package test.com.amrbsapp;

import com.amrbsapp.dao.impl.RoomImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import com.amrbsapp.entity.Room;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AMRBS Room App Tests")
public class RoomDAOTests {
    private Connection connection;
    private RoomImpl roomDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        // Initialize the in-memory database and DAO
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        roomDAO = new RoomImpl();

        // Set up schema and initial data
        try (var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE rooms (" +
                    "roomID INT PRIMARY KEY, " +
                    "capacity INT, " +
                    "isBooked BOOLEAN)");

            statement.execute("INSERT INTO rooms (roomID, capacity, isBooked) VALUES (1, 50, false)");
            statement.execute("INSERT INTO rooms (roomID, capacity, isBooked) VALUES (2, 30, true)");
        }
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }


    @Test
    public void testGetAllRooms() throws SQLException {
        List<Room> rooms = roomDAO.getAllRooms(connection);
        assertNotNull(rooms);
        assertEquals(2, rooms.size());
    }

    @Test
    public void testSaveRoom() throws SQLException {
        Room newRoom = new Room(3, 20, null, false);
        roomDAO.saveRoom(newRoom, connection);

        Room savedRoom = roomDAO.getRoomByID(3, connection);
        assertNotNull(savedRoom);
        assertEquals(3, savedRoom.getRoomID());
        assertEquals(20, savedRoom.getCapacity());
        assertFalse(savedRoom.isBooked());
    }

    @Test
    public void testUpdateRoom() throws SQLException {
        Room roomToUpdate = new Room(1, 100, null, true);
        roomDAO.updateRoom(roomToUpdate, connection);

        Room updatedRoom = roomDAO.getRoomByID(1, connection);
        assertNotNull(updatedRoom);
        assertEquals(100, updatedRoom.getCapacity());
        assertTrue(updatedRoom.isBooked());
    }

    @Test
    public void testDeleteRoom() throws SQLException {
        roomDAO.deleteRoom(2, connection);
        Room deletedRoom = roomDAO.getRoomByID(2, connection);
        assertNull(deletedRoom);
    }

    @Test
    public void testGetAvailableRooms() throws SQLException {
        List<Room> availableRooms = roomDAO.getAvailableRooms(connection);
        assertNotNull(availableRooms);
        assertEquals(1, availableRooms.size()); // Room with ID 1 should be available
    }
}
