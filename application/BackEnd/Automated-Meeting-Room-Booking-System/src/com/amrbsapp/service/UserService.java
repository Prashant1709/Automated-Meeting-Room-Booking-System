package com.amrbsapp.service;

import com.amrbsapp.entity.User;

import java.sql.Connection;
import java.util.List;

public interface UserService {
    String authenticateUser(String email, String password, Connection connection);
    User getUserById(int userID, Connection connection) throws UserNotFoundException;
    List<User> getAllUsers(Connection connection);
    void saveUser(User user,Connection connection);
    void updateUser(User user,Connection connection);
    void deleteUser(int userID,Connection connection);
    User login(String email, String password,Connection connection);
    void assignCredits(int userID, int credits,Connection connection);
}
