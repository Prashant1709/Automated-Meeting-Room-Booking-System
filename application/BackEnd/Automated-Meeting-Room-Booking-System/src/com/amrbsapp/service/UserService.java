package com.amrbsapp.service;

import com.amrbsapp.entity.User;

import java.util.List;

public interface UserService {
    User getUserById(int userID);
    List<User> getAllUsers();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(int userID);
    User login(String email, String password);
    void assignCredits(int userID, int credits);
}
