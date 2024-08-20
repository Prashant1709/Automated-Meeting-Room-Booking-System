package com.amrbsapp.service;

import com.amrbsapp.entity.Meeting;
import com.amrbsapp.entity.Room;

import java.util.List;

public interface MeetingService {

    Meeting getMeetingById(int id);
    List<Meeting> getAllMeetings();
    void saveMeeting(Meeting meeting);
    void updateMeeting(Meeting meeting);
    void deleteMeeting(int id);
    List<Meeting> getMeetingsByUserId(int userId);
    List<Meeting> getMeetingsByRoomId(int roomId);
    boolean checkMandatoryAmenities(Room room, String meetingType);

}
