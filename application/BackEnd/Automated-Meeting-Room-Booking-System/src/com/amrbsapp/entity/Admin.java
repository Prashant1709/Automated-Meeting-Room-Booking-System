package com.amrbsapp.entity;

import java.util.List;

public class Admin extends User {
    private List<User> users;
    private List<Room> rooms;

    public Admin(String userID, String name, String email, String phone, String role, List<User> users, List<Room> rooms) {
        super(userID, name, email, phone, role);
        this.users = users;
        this.rooms = rooms;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "users=" + users +
                ", rooms=" + rooms +
                '}';
    }

}