package com.amrbsapp.entity;

import java.util.List;
import java.util.ArrayList;

public class Admin extends User {
    private List<User> users;
    private List<Room> rooms;

    public Admin(String userID, String name, String email, String phone) {
        super(userID, name, email, phone, "Admin");
        this.users = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public Room createRoom(String roomID, int capacity, List<Amenity> amenities) {
        return new Room(roomID, capacity, amenities);
    }

    public void configureRoom(Room room, int capacity, List<Amenity> amenities) {
        room.setCapacity(capacity);
        room.setMandatoryAmenities(amenities);
        System.out.println("Room " + room.getRoomID() + " configured successfully.");
    }

    // Add Room method
    public void addRoom(Room newRoom) {
        for (Room room : rooms) {
            if (room.getRoomID().equals(newRoom.getRoomID())) {
                System.out.println("Room with ID " + newRoom.getRoomID() + " already exists.");
                return;
            }
        }
        rooms.add(newRoom);
        System.out.println("Room " + newRoom.getRoomID() + " added successfully.");
    }

    // Remove Room method
    public void removeRoom(String roomID) {
        Room roomToRemove = null;
        for (Room room : rooms) {
            if (room.getRoomID().equals(roomID)) {
                roomToRemove = room;
                break;
            }
        }
        if (roomToRemove != null) {
            rooms.remove(roomToRemove);
            System.out.println("Room with ID " + roomID + " removed successfully.");
        } else {
            System.out.println("Room with ID " + roomID + " not found.");
        }
    }

    // Update Room method
    public void updateRoom(String roomID, int capacity, List<Amenity> amenities, boolean isBooked) {
        Room roomToUpdate = null;
        for (Room room : rooms) {
            if (room.getRoomID().equals(roomID)) {
                roomToUpdate = room;
                break;
            }
        }
        if (roomToUpdate != null) {
            roomToUpdate.setCapacity(capacity);
            roomToUpdate.setMandatoryAmenities(amenities);
            roomToUpdate.setBooked(isBooked);
            System.out.println("Room with ID " + roomID + " updated successfully.");
        } else {
            System.out.println("Room with ID " + roomID + " not found.");
        }
    }

    // View all rooms method (for reference)
    public void viewAllRooms() {
        System.out.println("List of all rooms:");
        for (Room room : rooms) {
            room.displayRoomDetails();
        }
    }


    // Add User method
    public void addUser(User newUser) {
        for (User user : users) {
            if (user.getUserID().equals(newUser.getUserID())) {
                System.out.println("User with ID " + newUser.getUserID() + " already exists.");
                return;
            }
        }
        users.add(newUser);
        System.out.println("User " + newUser.getName() + " added successfully.");
    }

    // Remove User method
    public void removeUser(String userID) {
        User userToRemove = null;
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                userToRemove = user;
                break;
            }
        }
        if (userToRemove != null) {
            users.remove(userToRemove);
            System.out.println("User with ID " + userID + " removed successfully.");
        } else {
            System.out.println("User with ID " + userID + " not found.");
        }
    }

    // Update User method
    public void updateUser(String userID, String name, String email, String phone, String role) {
        User userToUpdate = null;
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                userToUpdate = user;
                break;
            }
        }
        if (userToUpdate != null) {
            userToUpdate.setName(name);
            userToUpdate.setEmail(email);
            userToUpdate.setPhone(phone);
            userToUpdate.setRole(role);
            System.out.println("User with ID " + userID + " updated successfully.");
        } else {
            System.out.println("User with ID " + userID + " not found.");
        }
    }

    // View all users method
    public void viewAllUsers() {
        System.out.println("List of all users:");
        for (User user : users) {
            user.viewProfile();
        }
    }

}

