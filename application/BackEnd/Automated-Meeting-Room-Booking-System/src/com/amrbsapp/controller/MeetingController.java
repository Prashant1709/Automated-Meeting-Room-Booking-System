package com.amrbsapp.controller;

import com.amrbsapp.entity.Meeting;
import com.amrbsapp.entity.Room;
import com.amrbsapp.service.MeetingService;
import com.amrbsapp.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class MeetingController {
    private final MeetingService meetingService;
    private final Connection conn = DBConnection.getConnection("amrbsapp");
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }
    public Meeting getMeeting(int meetingID){
        return meetingService.getMeetingById(meetingID,conn);
    }
    public List<Meeting> getAllMeetings(){
        return meetingService.getAllMeetings(conn);
    }
    public boolean addMeeting(Meeting meeting){

        return meetingService.saveMeeting(meeting,conn);
    }
    public boolean updateMeeting(Meeting meeting){
        return meetingService.updateMeeting(meeting,conn);
    }
    public boolean deleteMeeting(int meetingID){
        return meetingService.deleteMeeting(meetingID,conn);
    }
    public List<Meeting> getMeetingsByRoomId(int roomID){
        return meetingService.getMeetingsByRoomId(roomID,conn);
    }
    public List<Meeting> getMeetingsByUserId(int userID){
        return meetingService.getMeetingsByUserId(userID,conn);
    }
    public boolean checkMandatoryAmenities(Room room, String meetingType){
        return meetingService.checkMandatoryAmenities(room,meetingType,conn);
    }
}
