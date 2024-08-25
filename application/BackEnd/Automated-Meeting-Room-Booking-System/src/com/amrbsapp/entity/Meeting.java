package com.amrbsapp.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Meeting {
    private int meetingID;
    private Room room;
    private List<User> participants;
    private LocalDateTime meetingDate;
    private MeetingType meetingType;
    private int duration;
    private List<Amenity> amenities;

    public Meeting(int meetingID, Room room, List<User> participants, LocalDateTime meetingDate, MeetingType meetingType, int duration, List<Amenity> amenities) {
        this.meetingID = meetingID;
        this.room = room;
        this.participants = participants;
        this.meetingDate = meetingDate;
        this.meetingType = meetingType;
        this.duration = duration;
        this.amenities = amenities;
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
    }

    public MeetingType getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(MeetingType meetingType) {
        this.meetingType = meetingType;
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
    public String toJSON() {
        return "{" +
                "\"meetingID\":\"" + meetingID + "\"," +
                "\"room\":" + room.toJson() + "," +
                "\"participants\":" + participants + "," +
                "\"meetingDate\":\"" + meetingDate + "\"," +
                "\"meetingType\":\"" + meetingType + "\"," +
                "\"duration\":" + duration +
                "}";
    }
    public List<Amenity> getAmenities() {
        return amenities;
    }
}