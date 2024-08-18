package com.amrbsapp.dao;

import com.amrbsapp.entity.User;

import java.util.List;

public interface UserDAO {
    User gerUserById(int id);
    List<User> getAllUsers();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User getUserByEmail(String email);
}
