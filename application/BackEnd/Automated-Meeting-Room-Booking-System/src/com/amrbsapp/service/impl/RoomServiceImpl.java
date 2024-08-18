package com.amrbsapp.service.impl;

import com.amrbsapp.dao.RoomDAO;
import com.amrbsapp.entity.Room;
import com.amrbsapp.service.RoomService;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private RoomDAO roomDAO;
    public RoomServiceImpl(RoomDAO roomDAO) {
        this.roomDAO = roomDAO;
    }
    @Override
    public Room getRoomById(int id) {
        return roomDAO.getRoomByID(id);
    }

    @Override
    public List<Room> getAllRooms() {
        return List.of();
    }

    @Override
    public void saveRoom(Room room) {

    }

    @Override
    public void updateRoom(Room room) {

    }

    @Override
    public void deleteRoom(int id) {

    }

    @Override
    public List<Room> getAvailableRooms() {
        return List.of();
    }
}
