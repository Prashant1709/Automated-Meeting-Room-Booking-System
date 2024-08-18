package com.amrbsapp.dao.impl;

import com.amrbsapp.dao.MeetingDAO;
import com.amrbsapp.entity.Meeting;

import java.util.List;

public class MeetingImpl implements MeetingDAO {
    @Override
    public Meeting getMeetingByID(int meetingID) {
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
    public void deleteMeeting(int meetingID) {

    }

    @Override
    public List<Meeting> getMeetingsByUserID(int userID) {
        return List.of();
    }

    @Override
    public List<Meeting> getMeetingsByRoomID(int roomID) {
        return List.of();
    }
}
