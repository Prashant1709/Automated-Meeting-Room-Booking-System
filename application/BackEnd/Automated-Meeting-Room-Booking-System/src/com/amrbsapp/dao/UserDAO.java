package com.amrbsapp.dao;

import com.amrbsapp.entity.User;
import com.amrbsapp.exception.UserNotFoundException;

import java.sql.Connection;
import java.util.List;

public interface UserDAO {
    User gerUserById(int id, Connection connection) throws UserNotFoundException;
    String authenticateUser(String email, String password, Connection connection);
    List<User> getAllUsers(Connection connection);
    void saveUser(User user,Connection connection);
    void updateUser(User user,Connection connection) throws UserNotFoundException;
    void deleteUser(int id,Connection connection) throws UserNotFoundException;
    User getUserByEmail(String email,Connection connection);
    void assignCredits(int id, int credits,Connection connection);
}
