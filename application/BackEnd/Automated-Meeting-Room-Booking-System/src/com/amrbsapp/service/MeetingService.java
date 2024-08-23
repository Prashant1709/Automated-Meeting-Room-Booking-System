package com.amrbsapp.service;

import com.amrbsapp.entity.Meeting;
import com.amrbsapp.entity.Room;

import java.sql.Connection;
import java.util.List;

public interface MeetingService {

    Meeting getMeetingById(int id, Connection conn);
    List<Meeting> getAllMeetings(Connection conn);
    boolean saveMeeting(Meeting meeting, Connection conn);
    boolean updateMeeting(Meeting meeting, Connection conn);
    boolean deleteMeeting(int id, Connection conn);
    List<Meeting> getMeetingsByUserId(int userId, Connection conn);
    List<Meeting> getMeetingsByRoomId(int roomId, Connection conn);
    boolean checkMandatoryAmenities(Room room, String meetingType, Connection conn);

}
