package com.amrbsapp.service.impl;

import com.amrbsapp.dao.RoomDAO;
import com.amrbsapp.entity.Room;
import com.amrbsapp.exception.RoomNotAvailableException;
import com.amrbsapp.service.RoomService;
import com.amrbsapp.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private RoomDAO roomDAO;
    public RoomServiceImpl(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }
    @Override
    public Room getRoomById(int id, Connection connection) throws RoomNotAvailableException {
        return roomDAO.getRoomByID(id, connection);
    }

    @Override
    public List<Room> getAllRooms(Connection connection) {
        return roomDAO.getAllRooms(connection);
    }

    @Override
    public void saveRoom(Room room, Connection connection) {
        roomDAO.saveRoom(room, connection);
    }

    @Override
    public void updateRoom(Room room,Connection connection) throws RoomNotAvailableException  {
        roomDAO.updateRoom(room,connection);
    }

    @Override
    public void deleteRoom(int id, Connection connection) throws RoomNotAvailableException {
        roomDAO.deleteRoom(id, connection);
    }

    @Override
    public List<Room> getAvailableRooms(Connection connection) {
        return roomDAO.getAvailableRooms(connection);
    }
}
