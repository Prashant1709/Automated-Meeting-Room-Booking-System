package com.amrbsapp.controller;

import com.amrbsapp.entity.Room;
import com.amrbsapp.service.RoomService;

import java.util.List;

public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    Room getRoom(int roomID){
        return roomService.getRoomById(roomID);
    }
    List<Room> getAllRooms(){
        return null;
    }
    void addRoom(Room room){

    }
    void updateRoom(Room room){

    }
    void deleteRoom(int roomID){

    }
    List<Room> getAvailableRooms(){
        return null;
    }
}
