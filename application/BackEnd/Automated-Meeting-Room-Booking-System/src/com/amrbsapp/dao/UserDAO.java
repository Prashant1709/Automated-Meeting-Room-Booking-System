package com.amrbsapp.dao;

import com.amrbsapp.entity.User;

import java.sql.Connection;
import java.util.List;

public interface UserDAO {
    User gerUserById(int id, Connection connection);
    List<User> getAllUsers(Connection connection);
    void saveUser(User user,Connection connection);
    void updateUser(User user,Connection connection);
    void deleteUser(int id,Connection connection);
    User getUserByEmail(String email,Connection connection);
}
