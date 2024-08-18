package com.amrbsapp.service;

import com.amrbsapp.entity.Room;

import java.util.List;

public interface RoomService {
    Room getRoomById(int id);
    List<Room> getAllRooms();
    void saveRoom(Room room);
    void updateRoom(Room room);
    void deleteRoom(int id);
    List<Room> getAvailableRooms();
}
