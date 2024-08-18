package com.amrbsapp.dao;

import com.amrbsapp.entity.Meeting;

import java.util.List;

public interface MeetingDAO {

    Meeting getMeetingByID(int meetingID);
    List<Meeting> getAllMeetings();
    void saveMeeting(Meeting meeting);
    void updateMeeting(Meeting meeting);
    void deleteMeeting(int meetingID);
    List<Meeting> getMeetingsByUserID(int userID);
    List<Meeting> getMeetingsByRoomID(int roomID);

}
