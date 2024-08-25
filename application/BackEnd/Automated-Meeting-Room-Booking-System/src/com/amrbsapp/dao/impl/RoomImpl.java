package com.amrbsapp.dao.impl;
import com.amrbsapp.dao.RoomDAO;
import com.amrbsapp.entity.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomImpl implements RoomDAO {

    @Override
    public Room getRoomByID(int roomID, Connection connection) {
        String query = "SELECT * FROM amrbsapp.rooms WHERE roomID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, roomID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Room(
                            resultSet.getInt("roomID"),
                            resultSet.getInt("capacity"),
                            null,
                            resultSet.getBoolean("isBooked")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Room> getAllRooms(Connection connection) {
        String query = "SELECT * FROM amrbsapp.rooms";
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                rooms.add(new Room(
                        resultSet.getInt("roomID"),
                        resultSet.getInt("capacity"),
                        null,
                        resultSet.getBoolean("isBooked")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public void saveRoom(Room room, Connection connection) {
        String query = "INSERT INTO amrbsapp.rooms (roomID, capacity, isBooked) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, room.getRoomID());
            preparedStatement.setInt(2, room.getCapacity());
            preparedStatement.setBoolean(3, room.isBooked());
            int rowsAffected = preparedStatement.executeUpdate();
            connection.commit();
            if (rowsAffected > 0) {
                System.out.println("Room saved successfully");
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                e.printStackTrace();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    }

    @Override
    public void updateRoom(Room room, Connection connection) {
        String query = "UPDATE amrbsapp.rooms SET capacity = ?, isBooked = ? WHERE roomID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, room.getCapacity());
            preparedStatement.setBoolean(2, room.isBooked());
            preparedStatement.setInt(3, room.getRoomID());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Room updated successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRoom(int roomID, Connection connection) {
        String query = "DELETE FROM amrbsapp.rooms WHERE roomID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, roomID);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Room deleted successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Room> getAvailableRooms(Connection connection) {
        String query = "SELECT * FROM amrbsapp.rooms WHERE isBooked = false";
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                rooms.add(new Room(
                        resultSet.getInt("roomID"),
                        resultSet.getInt("capacity"),
                        null,
                        resultSet.getBoolean("isBooked")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
