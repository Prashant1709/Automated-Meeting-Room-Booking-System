package com.amrbsapp.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Meeting {
    private String meetingID;
    private Room room;
    private List<User> participants;
    private LocalDateTime meetingDate;
    private String meetingType;
    private int duration;

    public Meeting(String meetingID, Room room, List<User> participants, LocalDateTime meetingDate, String meetingType, int duration) {
        this.meetingID = meetingID;
        this.room = room;
        this.participants = participants;
        this.meetingDate = meetingDate;
        this.meetingType = meetingType;
        this.duration = duration;
    }

    public String getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(String meetingID) {
        this.meetingID = meetingID;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public LocalDateTime getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDateTime meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingID='" + meetingID + '\'' +
                ", room=" + room +
                ", participants=" + participants +
                ", meetingDate=" + meetingDate +
                ", meetingType='" + meetingType + '\'' +
                ", duration=" + duration +
                '}';
    }
}