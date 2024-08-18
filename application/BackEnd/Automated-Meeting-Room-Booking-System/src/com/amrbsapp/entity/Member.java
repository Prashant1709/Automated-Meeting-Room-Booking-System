package com.amrbsapp.entity;

import java.util.List;

public class Member extends User {
    public Member(String userID, String name, String email, String phone) {
        super(userID, name, email, phone, "Member");
    }

    // View Meetings method
    public void viewMeetings(List<Meeting> meetings) {
        System.out.println("Viewing all meetings for member: " + this.getName());
        for (Meeting meeting : meetings) {
            if (meeting.getParticipants().contains(this)) {
                System.out.println("Meeting ID: " + meeting.getMeetingID() + ", Room: " + meeting.getRoom().getRoomID() + ", Date: " + meeting.getMeetingDate());
            }
        }
    }
}
