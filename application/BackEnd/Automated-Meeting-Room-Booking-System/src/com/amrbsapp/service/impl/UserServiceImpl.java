package com.amrbsapp.service.impl;

import com.amrbsapp.entity.User;
import com.amrbsapp.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(int userID) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(int userID) {

    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public void assignCredits(int userID, int credits) {

    }
}
