package com.amrbsapp.service.impl;

import com.amrbsapp.entity.User;
import com.amrbsapp.service.UserService;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(int userID, Connection connection)  {

        return null;
    }

    @Override
    public List<User> getAllUsers(Connection connection) {
        return List.of();
    }

    @Override
    public void saveUser(User user, Connection connection) {

    }

    @Override
    public void updateUser(User user, Connection connection) {

    }

    @Override
    public void deleteUser(int userID, Connection connection) {

    }

    @Override
    public User login(String email, String password, Connection connection) {
        return null;
    }

    @Override
    public void assignCredits(int userID, int credits, Connection connection) {

    }
}
