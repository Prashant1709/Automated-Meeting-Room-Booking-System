package test.com.amrbsapp;

import com.amrbsapp.dao.impl.MeetingImpl;
import com.amrbsapp.entity.*;
import org.junit.jupiter.api.DisplayName;

import com.amrbsapp.dao.impl.AmenityImpl;
import com.amrbsapp.dao.impl.BookingImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


import java.util.List;

import static org.junit.Assert.*;

@DisplayName("AMRBS Meeting App Tests")
public class MeetingDAOTests {

    private static Connection connection;
    private MeetingImpl meetingDAO;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // Set up the database connection
        String url = "jdbc:mysql://localhost:3306/amrbsapp_test"; // Use your test DB
        String username = "root";
        String password = "password"; // Use the appropriate password
        connection = DriverManager.getConnection(url, username, password);
    }

    @Before
    public void setUp() {
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
        Meeting meeting = new Meeting(0, room, participants, LocalDateTime.now(), MeetingType.CONFERENCE, 2, amenities);

        boolean isSaved = meetingDAO.saveMeeting(meeting, connection);
        assertTrue("Meeting should be saved successfully", isSaved);

        List<Meeting> meetings = meetingDAO.getAllMeetings(connection);
        boolean found = false;
        for (Meeting m : meetings) {
            if (m.getRoom().getRoomID() == 101 && m.getMeetingType() == MeetingType.CONFERENCE) {
                found = true;
                break;
            }
        }
        assertTrue("Meeting should be found in the database", found);
    }

    @Test
    public void testGetMeetingByID() throws Exception {
        // Assuming a meeting with ID 1 exists in your test database
        Meeting meeting = meetingDAO.getMeetingByID(1, connection);

        assertNotNull(meeting);
        assertEquals(1, meeting.getMeetingID());
        assertEquals("Admin User", meeting.getParticipants().get(0).getName()); // Replace with actual data from your DB
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
        Meeting meeting = new Meeting(1, room, participants, LocalDateTime.now().plusDays(1), MeetingType.MEETING, 3, amenities);

        boolean isUpdated = meetingDAO.updateMeeting(meeting, connection);
        assertTrue("Meeting should be updated successfully", isUpdated);

        Meeting updatedMeeting = meetingDAO.getMeetingByID(1, connection);
        assertEquals(102, updatedMeeting.getRoom().getRoomID());
        assertEquals(MeetingType.MEETING, updatedMeeting.getMeetingType());
    }

    @Test
    public void testDeleteMeeting() throws Exception {
        // Assuming a meeting with ID 2 exists
        boolean isDeleted = meetingDAO.deleteMeeting(2, connection);
        assertTrue("Meeting should be deleted successfully", isDeleted);

        Meeting deletedMeeting = meetingDAO.getMeetingByID(2, connection);
        assertNull(deletedMeeting);
    }

    @Test
    public void testGetAllMeetings() throws Exception {
        // Insert multiple meetings to test retrieval
        Room room1 = new Room(103, 30, null, true);
        Room room2 = new Room(104, 20, null, true);
        Meeting meeting1 = new Meeting(0, room1, null, LocalDateTime.now().plusDays(2), MeetingType.CONFERENCE, 1, null);
        Meeting meeting2 = new Meeting(0, room2, null, LocalDateTime.now().plusDays(3), MeetingType.MEETING, 2, null);
        meetingDAO.saveMeeting(meeting1, connection);
        meetingDAO.saveMeeting(meeting2, connection);

        List<Meeting> meetings = meetingDAO.getAllMeetings(connection);

        assertTrue("Should retrieve at least 2 meetings", meetings.size() >= 2);
    }

    @Test
    public void testGetMeetingsByUserID() throws Exception {
        // Assuming a meeting with userID 1 exists
        List<Meeting> meetings = meetingDAO.getMeetingsByUserID(1, connection);
        assertNotNull(meetings);
        assertTrue("Meetings list should not be empty", !meetings.isEmpty());

        for (Meeting meeting : meetings) {
            boolean hasUser = false;
            for (User participant : meeting.getParticipants()) {
                if (participant.getUserID() == 1) {
                    hasUser = true;
                    break;
                }
            }
            assertTrue("User 1 should be a participant in this meeting", hasUser);
        }
    }

    @Test
    public void testGetMeetingsByRoomID() throws Exception {
        // Assuming a meeting with roomID 101 exists
        List<Meeting> meetings = meetingDAO.getMeetingsByRoomID(101, connection);
        assertNotNull(meetings);
        assertTrue("Meetings list should not be empty", !meetings.isEmpty());

        for (Meeting meeting : meetings) {
            assertEquals(101, meeting.getRoom().getRoomID());
        }
    }

    @Test
    public void testCheckMandatoryAmenities() throws Exception {
        // Assuming certain amenities are mandatory for specific meeting types
        Room room = new Room(101, 50, null, true);
        boolean result = meetingDAO.checkMandatoryAmenities(room, "CONFERENCE", connection);
        assertFalse("Mandatory amenities check should return false if not met", result);

        // If you want to simulate the check being true, ensure the amenities are set up correctly in the test database
    }

    @After
    public void tearDown() throws Exception {
        // Optionally clean up database after each test
        connection.createStatement().execute("DELETE FROM meetings WHERE roomID IN (101, 102, 103, 104)");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
