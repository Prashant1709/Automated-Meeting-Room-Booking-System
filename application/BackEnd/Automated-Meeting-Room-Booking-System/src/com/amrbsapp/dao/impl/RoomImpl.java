package com.amrbsapp.dao.impl;

import com.amrbsapp.dao.RoomDAO;
import com.amrbsapp.entity.Room;
import com.amrbsapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoomImpl implements RoomDAO {
    @Override
    public Room getRoomByID(int roomID) {
        Room room = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM room WHERE room_id = ?");
            preparedStatement.setInt(1, roomID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                room = new Room(resultSet.getString("room_id"), resultSet.getInt("capacity"), null, resultSet.getBoolean("is_booked"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                DBConnection.closeConnection(connection);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return room;
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
    public void deleteRoom(int roomID) {

    }

    @Override
    public List<Room> getAvailableRooms() {
        return List.of();
    }
}
