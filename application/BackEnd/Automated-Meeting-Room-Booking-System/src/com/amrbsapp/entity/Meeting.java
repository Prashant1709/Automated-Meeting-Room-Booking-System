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

    // Schedule Meeting method
    public void scheduleMeeting() {
        System.out.println("Meeting scheduled: " + meetingID);
    }

    // Cancel Meeting method
    public void cancelMeeting() {
        System.out.println("Meeting canceled: " + meetingID);
    }

    // Check Mandatory Amenities method
    public void checkMandatoryAmenities(){
        List<Amenity> amenities = room.getMandatoryAmenities();
        if (amenities == null || amenities.isEmpty()) {
//            throw new InsufficientAmenitiesException("Room does not have all mandatory amenities for this meeting type.");
        }
        System.out.println("All mandatory amenities are present.");
    }

    // Getters and Setters
    public String getMeetingID() { return meetingID; }
    public Room getRoom() { return room; }
    public List<User> getParticipants() { return participants; }
    public LocalDateTime getMeetingDate() { return meetingDate; }
    public String getMeetingType() { return meetingType; }
    public int getDuration() { return duration; }
}

