package com.amrbsapp.dao;

import com.amrbsapp.entity.Room;
import com.amrbsapp.exception.RoomNotAvailableException;

import java.sql.Connection;
import java.util.List;

public interface RoomDAO {
    Room getRoomByID(int roomID, Connection connection) throws RoomNotAvailableException;
    List<Room> getAllRooms(Connection connection);
    void saveRoom(Room room, Connection connection);
    void updateRoom(Room room,Connection connection) throws RoomNotAvailableException;
    void deleteRoom(int roomID, Connection connection) throws RoomNotAvailableException;
    List<Room> getAvailableRooms(Connection connection);
}
