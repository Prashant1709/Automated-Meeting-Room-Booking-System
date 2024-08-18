package com.amrbsapp.controller;

import com.amrbsapp.entity.Meeting;
import com.amrbsapp.entity.Room;

import java.util.List;

public class MeetingController {
    Meeting getMeeting(int meetingID){
        return null;
    }
    List<Meeting> getAllMeetings(){
        return null;
    }
    void addMeeting(Meeting meeting){

    }
    void updateMeeting(Meeting meeting){

    }
    void deleteMeeting(int meetingID){

    }
    List<Meeting> getMeetingsByRoomId(int roomID){
        return null;
    }
    List<Meeting> getMeetingsByUserId(int userID){
        return null;
    }
    boolean checkMandatoryAmenities(Room room, String meetingType){
        return false;
    }
}
