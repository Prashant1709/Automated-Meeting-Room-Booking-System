package com.amrbsapp.service.impl;

import com.amrbsapp.dao.MeetingDAO;
import com.amrbsapp.entity.Meeting;
import com.amrbsapp.entity.Room;
import com.amrbsapp.service.MeetingService;

import java.sql.Connection;
import java.util.List;

public class MeetingServiceImpl implements MeetingService {

    private MeetingDAO meetingDAO;
    public MeetingServiceImpl(MeetingDAO meetingDAO) {
        this.meetingDAO = meetingDAO;
    }
    @Override
    public Meeting getMeetingById(int id, Connection conn) {
        return meetingDAO.getMeetingByID(id, conn);
    }

    @Override
    public List<Meeting> getAllMeetings(Connection conn) {
        return meetingDAO.getAllMeetings(conn);
    }

    @Override
    public boolean saveMeeting(Meeting meeting, Connection conn) {
        return meetingDAO.saveMeeting(meeting, conn);
    }

    @Override
    public boolean updateMeeting(Meeting meeting, Connection conn) {
        return meetingDAO.updateMeeting(meeting, conn);
    }

    @Override
    public boolean deleteMeeting(int id, Connection conn) {
        return meetingDAO.deleteMeeting(id, conn);
    }

    @Override
    public List<Meeting> getMeetingsByUserId(int userId, Connection conn) {
        return meetingDAO.getMeetingsByUserID(userId, conn);
    }

    @Override
    public List<Meeting> getMeetingsByRoomId(int roomId, Connection conn) {
        return meetingDAO.getMeetingsByRoomID(roomId, conn);
    }

    @Override
    public boolean checkMandatoryAmenities(Room room, String meetingType, Connection conn) {
        return meetingDAO.checkMandatoryAmenities(room, meetingType, conn);
    }
}
