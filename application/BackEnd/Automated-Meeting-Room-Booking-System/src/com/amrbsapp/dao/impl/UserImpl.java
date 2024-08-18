package com.amrbsapp.dao.impl;

import com.amrbsapp.dao.UserDAO;
import com.amrbsapp.entity.User;

import java.util.List;

public class UserImpl implements UserDAO {
    @Override
    public User gerUserById(int id) {
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
    public void deleteUser(int id) {

    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }
}
