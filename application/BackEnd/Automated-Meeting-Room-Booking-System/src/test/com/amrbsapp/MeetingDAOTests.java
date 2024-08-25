package test.com.amrbsapp;

import com.amrbsapp.dao.impl.MeetingImpl;
import com.amrbsapp.entity.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AMRBS Meeting App Tests")
public class MeetingDAOTests {

    private static Connection connection;
    private static MeetingImpl meetingDAO;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        // Set up the database connection
        String url = "jdbc:mysql://localhost:3306/amrbsapp_test"; // Use your test DB
        String username = "root";
        String password = "password"; // Use the appropriate password
        connection = DriverManager.getConnection(url, username, password);
    }

    @BeforeAll
    public static void setUp() {
        meetingDAO = new MeetingImpl();
    }

    @Test
    public void testSaveMeeting() throws Exception {
        Room room = new Room(101, 50, null, true);
        List<User> participants = Arrays.asList(
                new Admin(1, "Admin User", "admin@example.com", "pass", RoleType.ADMIN),
                new Manager(2, "Manager User", "manager@example.com", "pass", RoleType.MANAGER, 100)
        );
        List<Amenity> amenities = Arrays.asList(
                new Amenity(1, "Projector", 50),
                new Amenity(2, "Whiteboard", 20)
        );
        Meeting meeting = new Meeting(0, room, participants, LocalDateTime.now(), MeetingType.CONFERENCE_CALL, 2, amenities);

        boolean isSaved = meetingDAO.saveMeeting(meeting, connection);
        assertTrue(isSaved);

        List<Meeting> meetings = meetingDAO.getAllMeetings(connection);
        boolean found = false;
        for (Meeting m : meetings) {
            if (m.getRoom().getRoomID() == 101 && m.getMeetingType() == MeetingType.CONFERENCE_CALL) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testUpdateMeeting() throws Exception {
        // Assuming a meeting with ID 1 exists
        Room room = new Room(102, 60, null, true);
        List<User> participants = Arrays.asList(
                new Manager(2, "Manager User", "manager@example.com", "pass", RoleType.MANAGER, 100)
        );
        List<Amenity> amenities = Arrays.asList(
                new Amenity(2, "Whiteboard", 20)
        );
        Meeting meeting = new Meeting(1, room, participants, LocalDateTime.now().plusDays(1), MeetingType.CLASSROOM_TRAINING, 3, amenities);

        boolean isUpdated = meetingDAO.updateMeeting(meeting, connection);
        assertTrue(isUpdated);

        Meeting updatedMeeting = meetingDAO.getMeetingByID(1, connection);
        assertEquals(102, updatedMeeting.getRoom().getRoomID());
        assertEquals(MeetingType.BUSINESS, updatedMeeting.getMeetingType());
    }

    @Test
    public void testDeleteMeeting() throws Exception {
        // Assuming a meeting with ID 2 exists
        boolean isDeleted = meetingDAO.deleteMeeting(2, connection);
        assertTrue(isDeleted);

        Meeting deletedMeeting = meetingDAO.getMeetingByID(2, connection);
        assertNull(deletedMeeting);
    }

    @Test
    public void testGetAllMeetings() throws Exception {
        // Insert multiple meetings to test retrieval
        Room room1 = new Room(103, 30, null, true);
        Room room2 = new Room(104, 20, null, true);
        Meeting meeting1 = new Meeting(0, room1, null, LocalDateTime.now().plusDays(2), MeetingType.CONFERENCE_CALL, 1, null);
        Meeting meeting2 = new Meeting(0, room2, null, LocalDateTime.now().plusDays(3), MeetingType.BUSINESS, 2, null);
        meetingDAO.saveMeeting(meeting1, connection);
        meetingDAO.saveMeeting(meeting2, connection);

        List<Meeting> meetings = meetingDAO.getAllMeetings(connection);

        assertTrue( meetings.size() >= 2);
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
