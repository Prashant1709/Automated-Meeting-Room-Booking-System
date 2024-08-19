package com.amrbsapp.dao.impl;

import com.amrbsapp.dao.UserDAO;
import com.amrbsapp.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserDAO {

    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM amrbsapp.users WHERE userID=?";
    private static final String GET_ALL_USERS_QUERY = "SELECT * FROM amrbsapp.users";
    private static final String INSERT_USER_QUERY = "INSERT INTO amrbsapp.users(userID, name, email, password, role) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_QUERY = "UPDATE amrbsapp.users SET name=?, email=?, password=?, role=? WHERE userID=?";
    private static final String DELETE_USER_QUERY = "DELETE FROM amrbsapp.users WHERE userID=?";
    private static final String GET_USER_BY_EMAIL_QUERY = "SELECT * FROM amrbsapp.users WHERE email=?";

    @Override
    public User gerUserById(int id, Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID_QUERY)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            // Log the exception
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers(Connection connection) {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS_QUERY);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            // Log the exception
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void saveUser(User user, Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER_QUERY)) {
            mapUserToPreparedStatement(user, ps);
            int ra = ps.executeUpdate();
            if (ra == 1) {
                System.out.println("User saved successfully");
            } else {
                System.out.println("User not saved");
            }
        } catch (SQLException e) {
            // Log the exception
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user, Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_QUERY)) {
            mapUserToPreparedStatement(user, ps);
            ps.setString(5, user.getUserID());
            int ra = ps.executeUpdate();
            if (ra == 1) {
                System.out.println("User updated successfully");
            } else {
                System.out.println("User not updated");
            }
        } catch (SQLException e) {
            // Log the exception
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id, Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(DELETE_USER_QUERY)) {
            ps.setInt(1, id);
            int ra = ps.executeUpdate();
            if (ra == 1) {
                System.out.println("User deleted successfully");
            } else {
                System.out.println("User not deleted");
            }
        } catch (SQLException e) {
            // Log the exception
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByEmail(String email, Connection connection) {
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_EMAIL_QUERY)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            // Log the exception
            e.printStackTrace();
        }
        return null;
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        String role = rs.getString("role");
        if (role.equals("Admin")) {
            return new Admin(rs.getString("userID"), rs.getString("name"), rs.getString("email"), rs.getString("password"), RoleType.ADMIN, null, null);
        } else if (role.equals("Manager")) {
            return new Manager(rs.getString("userID"), rs.getString("name"), rs.getString("email"), rs.getString("password"), RoleType.MANAGER, rs.getInt("credits"));
        } else {
            return new Member(rs.getString("userID"), rs.getString("name"), rs.getString("email"), rs.getString("password"), RoleType.MEMBER);
        }
    }

    private void mapUserToPreparedStatement(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getUserID());
        ps.setString(2, user.getName());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPhone());
        ps.setString(5, user.getRole().toString());
    }
}
