package com.amrbsapp.dao;

import com.amrbsapp.entity.Meeting;
import com.amrbsapp.entity.Room;

import java.sql.Connection;
import java.util.List;

public interface MeetingDAO {

    Meeting getMeetingByID(int meetingID, Connection connection);
    List<Meeting> getAllMeetings(Connection connection);
    boolean saveMeeting(Meeting meeting, Connection connection);
    boolean updateMeeting(Meeting meeting, Connection connection);
    boolean deleteMeeting(int meetingID, Connection connection);
    List<Meeting> getMeetingsByUserID(int userID, Connection connection);
    List<Meeting> getMeetingsByRoomID(int roomID, Connection connection);
    boolean checkMandatoryAmenities(Room room, String meetingType, Connection connection);


}
