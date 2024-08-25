package com.amrbsapp.entity;
public class Admin extends User {
    public Admin(int userID, String name, String email, String password, RoleType role) {
        super(userID, name, email, password, role);
    }
}