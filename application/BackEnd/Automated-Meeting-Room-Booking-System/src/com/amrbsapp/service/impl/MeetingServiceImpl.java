package com.amrbsapp.service.impl;

import com.amrbsapp.entity.Meeting;
import com.amrbsapp.entity.Room;
import com.amrbsapp.service.MeetingService;

import java.util.List;

public class MeetingServiceImpl implements MeetingService {
    @Override
    public Meeting getMeetingById(int id) {
        return null;
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return List.of();
    }

    @Override
    public void saveMeeting(Meeting meeting) {

    }

    @Override
    public void updateMeeting(Meeting meeting) {

    }

    @Override
    public void deleteMeeting(int id) {

    }

    @Override
    public List<Meeting> getMeetingsByUserId(int userId) {
        return List.of();
    }

    @Override
    public List<Meeting> getMeetingsByRoomId(int roomId) {
        return List.of();
    }

    @Override
    public boolean checkMandatoryAmenities(Room room, String meetingType) {
        return false;
    }
}
