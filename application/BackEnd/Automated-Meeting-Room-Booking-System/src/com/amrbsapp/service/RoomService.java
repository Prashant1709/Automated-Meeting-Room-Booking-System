package com.amrbsapp.service;

import com.amrbsapp.entity.Room;

import java.sql.Connection;
import java.util.List;

public interface RoomService {
    Room getRoomById(int id, Connection connection);
    List<Room> getAllRooms(Connection connection);
    void saveRoom(Room room, Connection connection);
    void updateRoom(Room room,Connection connection);
    void deleteRoom(int id,Connection connection);
    List<Room> getAvailableRooms(Connection connection);
}
