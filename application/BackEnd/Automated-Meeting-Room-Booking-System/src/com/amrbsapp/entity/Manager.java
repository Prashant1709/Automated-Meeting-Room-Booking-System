package com.amrbsapp.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Manager extends User {
    private int credits;

    public Manager(int userID, String name, String email, String password, RoleType role, int credits) {
        super(userID, name, email, password, role);
        this.credits = credits;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "credits=" + credits +
                '}';
    }
}