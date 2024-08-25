package com.amrbsapp.controller;

import com.amrbsapp.entity.Room;
import com.amrbsapp.exception.RoomNotAvailableException;
import com.amrbsapp.service.RoomService;
import com.amrbsapp.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class RoomController {

    private RoomService roomService;
    private final Connection conn = DBConnection.getConnection();
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    public Room getRoom(int roomID) throws RoomNotAvailableException {
        return roomService.getRoomById(roomID,conn);
    }
    public List<Room> getAllRooms(){
        return roomService.getAllRooms(conn);
    }
    public void addRoom(Room room){
        roomService.saveRoom(room,conn);
    }
    public void updateRoom(Room room) throws RoomNotAvailableException {
        roomService.updateRoom(room,conn);
    }
    public void deleteRoom(int roomID) throws RoomNotAvailableException {
        roomService.deleteRoom(roomID,conn);
    }
    public List<Room> getAvailableRooms(){
        return roomService.getAvailableRooms(conn);
    }
}
