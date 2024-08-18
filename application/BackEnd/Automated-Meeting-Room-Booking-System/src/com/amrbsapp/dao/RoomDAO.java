package com.amrbsapp.dao;

import com.amrbsapp.entity.Room;

import java.util.List;

public interface RoomDAO {
    Room getRoomByID(int roomID);
    List<Room> getAllRooms();
    void saveRoom(Room room);
    void updateRoom(Room room);
    void deleteRoom(int roomID);
    List<Room> getAvailableRooms();
}
