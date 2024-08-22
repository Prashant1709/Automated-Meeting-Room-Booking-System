package com.amrbsapp.controller;

import com.amrbsapp.entity.Meeting;
import com.amrbsapp.entity.Room;
import com.amrbsapp.service.MeetingService;

import java.sql.Connection;
import java.util.List;

public class MeetingController {
    private final MeetingService meetingService;
    private Connection conn;
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }
    Meeting getMeeting(int meetingID){
        return meetingService.getMeetingById(meetingID,conn);
    }
    List<Meeting> getAllMeetings(){
        return meetingService.getAllMeetings(conn);
    }
    boolean addMeeting(Meeting meeting){
        return meetingService.saveMeeting(meeting,conn);
    }
    boolean updateMeeting(Meeting meeting){
        return meetingService.updateMeeting(meeting,conn);
    }
    boolean deleteMeeting(int meetingID){
        return meetingService.deleteMeeting(meetingID,conn);
    }
    List<Meeting> getMeetingsByRoomId(int roomID){
        return meetingService.getMeetingsByRoomId(roomID,conn);
    }
    List<Meeting> getMeetingsByUserId(int userID){
        return meetingService.getMeetingsByUserId(userID,conn);
    }
    boolean checkMandatoryAmenities(Room room, String meetingType){
        return meetingService.checkMandatoryAmenities(room,meetingType,conn);
    }
}
