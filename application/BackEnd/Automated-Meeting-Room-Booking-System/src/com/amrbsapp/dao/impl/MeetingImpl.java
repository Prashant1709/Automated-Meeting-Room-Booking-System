package com.amrbsapp.dao.impl;

import com.amrbsapp.dao.MeetingDAO;
import com.amrbsapp.entity.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingImpl implements MeetingDAO {


    @Override
    public Meeting getMeetingByID(int meetingID, Connection connection) {
        String query = "SELECT m.meetingID, m.roomID, m.meetingType, m.meetingDate, m.duration, " +
                "r.roomID, r.capacity, " +
                "u.userID, u.name, u.email, u.password, u.role, u.credits, " +
                "a.amenityID, a.name, a.creditCost " +
                "FROM meetings m " +
                "JOIN rooms r ON m.roomID = r.roomID " +
                "LEFT JOIN meeting_participants mp ON m.meetingID = mp.meetingID " +
                "LEFT JOIN users u ON mp.userID = u.userID " +
                "LEFT JOIN MeetingAmenities ma ON m.meetingID = ma.meetingID " +
                "LEFT JOIN amenities a ON ma.amenityID = a.amenityID " +
                "WHERE m.meetingID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, meetingID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return buildMeetingFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Meeting> getAllMeetings(Connection connection) {
        String query = "SELECT m.meetingID, m.roomID, m.meetingType, m.meetingDate, m.duration, " +
                "r.roomID, r.capacity, " +
                "u.userID, u.name, u.email, u.password, u.role, u.credits, " +
                "a.amenityID, a.name, a.creditCost " +
                "FROM meetings m " +
                "JOIN rooms r ON m.roomID = r.roomID " +
                "LEFT JOIN meeting_participants mp ON m.meetingID = mp.meetingID " +
                "LEFT JOIN users u ON mp.userID = u.userID " +
                "LEFT JOIN MeetingAmenities ma ON m.meetingID = ma.meetingID " +
                "LEFT JOIN amenities a ON ma.amenityID = a.amenityID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Meeting> meetings = new ArrayList<>();
            while (resultSet.next()) {
                meetings.add(buildMeetingFromResultSet(resultSet));
            }
            return meetings;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean saveMeeting(Meeting meeting, Connection connection) {
        String insertMeetingQuery = "INSERT INTO meetings (meetingID,roomID, meetingType, meetingDate, duration) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertMeetingQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, meeting.getMeetingID());
            preparedStatement.setInt(2, meeting.getRoom().getRoomID());
            preparedStatement.setString(3, meeting.getMeetingType().toString());
            preparedStatement.setTimestamp(4, java.sql.Timestamp.valueOf(meeting.getMeetingDate()));
            preparedStatement.setInt(5, meeting.getDuration());
            rowsAffected = preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int meetingID = generatedKeys.getInt(1);
                    insertParticipants(meetingID, meeting.getParticipants(), connection);
                    insertAmenities(meetingID, meeting.getAmenities(), connection);
                    //export the meeting json to a file
                    saveMeetingAsJson(meeting);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean updateMeeting(Meeting meeting, Connection connection) {
        String updateMeetingQuery = "UPDATE meetings SET roomID = ?, meetingType = ?, meetingDate = ?, duration = ? WHERE meetingID = ?";
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateMeetingQuery)) {
            preparedStatement.setInt(1, meeting.getRoom().getRoomID());
            preparedStatement.setString(2, meeting.getMeetingType().name());
            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(meeting.getMeetingDate()));
            preparedStatement.setInt(4, meeting.getDuration());
            preparedStatement.setInt(5, meeting.getMeetingID());
            rowsAffected = preparedStatement.executeUpdate();

            deleteParticipantsAndAmenities(meeting.getMeetingID(), connection);
            insertParticipants(meeting.getMeetingID(), meeting.getParticipants(), connection);
            insertAmenities(meeting.getMeetingID(), meeting.getAmenities(), connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected == 1;
    }

    @Override
    public boolean deleteMeeting(int meetingID, Connection connection) {
        String query = "DELETE FROM meetings WHERE meetingID = ?";
        int rowsAffected = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, meetingID);
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected == 1;
    }

    @Override
    public List<Meeting> getMeetingsByUserID(int userID, Connection connection) {
        String query = "SELECT m.meetingID, m.roomID, m.meetingType, m.meetingDate, m.duration, " +
                "r.roomID, r.capacity, " +
                "u.userID, u.name, u.email, u.password, u.role, u.credits, " +
                "a.amenityID, a.name, a.creditCost " +
                "FROM meetings m " +
                "JOIN rooms r ON m.roomID = r.roomID " +
                "JOIN meeting_participants mp ON m.meetingID = mp.meetingID " +
                "JOIN users u ON mp.userID = u.userID " +
                "LEFT JOIN MeetingAmenities ma ON m.meetingID = ma.meetingID " +
                "LEFT JOIN amenities a ON ma.amenityID = a.amenityID " +
                "WHERE u.userID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Meeting> meetings = new ArrayList<>();
                while (resultSet.next()) {
                    meetings.add(buildMeetingFromResultSet(resultSet));
                }
                return meetings;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Meeting> getMeetingsByRoomID(int roomID, Connection connection) {
        String query = "SELECT m.meetingID, m.roomID, m.meetingType, m.meetingDate, m.duration, " +
                "r.roomID, r.capacity, " +
                "u.userID, u.name, u.email, u.password, u.role, u.credits, " +
                "a.amenityID, a.name, a.creditCost " +
                "FROM meetings m " +
                "JOIN rooms r ON m.roomID = r.roomID " +
                "JOIN meeting_participants mp ON m.meetingID = mp.meetingID " +
                "JOIN users u ON mp.userID = u.userID " +
                "LEFT JOIN MeetingAmenities ma ON m.meetingID = ma.meetingID " +
                "LEFT JOIN amenities a ON ma.amenityID = a.amenityID " +
                "WHERE r.roomID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, roomID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Meeting> meetings = new ArrayList<>();
                while (resultSet.next()) {
                    meetings.add(buildMeetingFromResultSet(resultSet));
                }
                return meetings;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean checkMandatoryAmenities(Room room, String meetingType, Connection connection) {
        return false;
    }

    private Meeting buildMeetingFromResultSet(ResultSet resultSet) throws SQLException {
        int meetingID = resultSet.getInt("meetingID");
        int roomID = resultSet.getInt("roomID");
        int capacity = resultSet.getInt("capacity");
        Room room = new Room(roomID, capacity, null, true);

        LocalDateTime meetingDate = resultSet.getTimestamp("meetingDate").toLocalDateTime();
        MeetingType meetingType = MeetingType.valueOf(resultSet.getString("meetingType"));
        int duration = resultSet.getInt("duration");

        List<User> participants = new ArrayList<>();
        List<Amenity> amenities = new ArrayList<>();

        do {
            if (resultSet.getString("role").equals("Admin")) {
                participants.add(new Admin(
                        resultSet.getInt("userID"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        RoleType.ADMIN
                ));
            } else if (resultSet.getString("role").equals("Manager")) {
                participants.add(new Manager(
                        resultSet.getInt("userID"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        RoleType.MANAGER,
                        resultSet.getInt("credits")
                ));
            } else {
                participants.add(new Member(
                        resultSet.getInt("userID"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        RoleType.MEMBER
                ));
            }

            amenities.add(new Amenity(
                    resultSet.getInt("amenityID"),
                    resultSet.getString("name"),
                    resultSet.getInt("creditCost")
            ));
        } while (resultSet.next());

        return new Meeting(meetingID, room,participants, meetingDate ,meetingType, duration, amenities);
    }

    private void insertParticipants(int meetingID, List<User> participants, Connection connection) throws SQLException {
        String insertParticipantQuery = "INSERT INTO meeting_participants (meetingID, userID) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertParticipantQuery)) {
            for (User user : participants) {
                preparedStatement.setInt(1, meetingID);
                preparedStatement.setInt(2, user.getUserID());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    private void insertAmenities(int meetingID, List<Amenity> amenities, Connection connection) throws SQLException {
        String insertAmenityQuery = "INSERT INTO MeetingAmenities (meetingID, amenityID) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertAmenityQuery)) {
            for (Amenity amenity : amenities) {
                preparedStatement.setInt(1, meetingID);
                preparedStatement.setInt(2, amenity.getAmenityID());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
    }

    private void deleteParticipantsAndAmenities(int meetingID, Connection connection) throws SQLException {
        String deleteParticipantsQuery = "DELETE FROM meeting_participants WHERE meetingID = ?";
        String deleteAmenitiesQuery = "DELETE FROM MeetingAmenities WHERE meetingID = ?";

        try (PreparedStatement psDeleteParticipants = connection.prepareStatement(deleteParticipantsQuery);
             PreparedStatement psDeleteAmenities = connection.prepareStatement(deleteAmenitiesQuery)) {

            psDeleteParticipants.setInt(1, meetingID);
            psDeleteParticipants.executeUpdate();

            psDeleteAmenities.setInt(1, meetingID);
            psDeleteAmenities.executeUpdate();
        }
    }
    public static void saveMeetingAsJson(Meeting meeting) {
        // Convert the Meeting object to a JSON string
        String jsonString = meeting.toJSON();
        // Get the current working directory
        String currentDir = System.getProperty("user.dir");
        // Define the path to the application folder
        Path applicationDir = Paths.get(currentDir, "application");
        // Ensure the directory exists (create it if it doesn't)
        try {
            Files.createDirectories(applicationDir);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to create application directory.");
            return;
        }
        // Define the full path to the JSON file
        Path filePath = applicationDir.resolve("meeting.json");
        try {
            // Write the JSON string to the file
            Files.write(filePath, jsonString.getBytes());
            System.out.println("Meeting saved successfully as JSON in the application folder.");
            System.out.println("File saved at: " + filePath.toString()); // Print the directory
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to save the Meeting object as JSON.");
        }
    }
}
