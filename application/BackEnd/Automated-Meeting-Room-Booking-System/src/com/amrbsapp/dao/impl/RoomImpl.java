package com.amrbsapp.dao.impl;

import com.amrbsapp.dao.RoomDAO;
import com.amrbsapp.entity.Room;
import com.amrbsapp.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomImpl implements RoomDAO {
    @Override
    public Room getRoomByID(int roomID, Connection connection) {
        Room room = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM amrbsapp.rooms WHERE roomID = ?");
            preparedStatement.setInt(1, roomID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                room = new Room(resultSet.getInt("roomID"), resultSet.getInt("capacity"), null, resultSet.getBoolean("isBooked"));
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
//                DBConnection.closeConnection(connection);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return room;
    }

    @Override
    public List<Room> getAllRooms(Connection connection) {
        List<Room> rooms = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM amrbsapp.rooms");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                rooms.add(new Room(resultSet.getInt("roomID"), resultSet.getInt("capacity"), null, resultSet.getBoolean("isBooked")));
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
//                DBConnection.closeConnection(connection);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return rooms;
    }

    @Override
    public void saveRoom(Room room,Connection connection) {
        // Save room to database using transaction
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("INSERT INTO amrbsapp.rooms (roomID, capacity, isBooked) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, room.getRoomID());
            preparedStatement.setInt(2, room.getCapacity());
            preparedStatement.setBoolean(3, room.isBooked());
            preparedStatement.executeUpdate();
            connection.commit();
        }catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
//                DBConnection.closeConnection(connection);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void updateRoom(Room room, Connection connection) {
        // Update room in database
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE amrbsapp.rooms SET capacity = ?, isBooked = ? WHERE roomID = ?");
            preparedStatement.setInt(1, room.getCapacity());
            preparedStatement.setBoolean(2, room.isBooked());
            preparedStatement.setInt(3, room.getRoomID());
            preparedStatement.executeUpdate();
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
//                DBConnection.closeConnection(connection);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteRoom(int roomID, Connection connection) {
        // Delete room from database
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM amrbsapp.rooms WHERE roomID = ?");
            preparedStatement.setInt(1, roomID);
            preparedStatement.executeUpdate();
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
//                DBConnection.closeConnection(connection);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Room> getAvailableRooms(Connection connection) {
        // Get all available rooms from database
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Room> rooms = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM amrbsapp.rooms WHERE isBooked = false");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                rooms.add(new Room(resultSet.getInt("roomID"), resultSet.getInt("capacity"), null, resultSet.getBoolean("isBooked")));
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
//                DBConnection.closeConnection(connection);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return rooms;
    }
}
