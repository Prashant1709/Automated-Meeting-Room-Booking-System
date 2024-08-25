package com.amrbsapp.service.impl;

import com.amrbsapp.dao.UserDAO;
import com.amrbsapp.entity.User;
import com.amrbsapp.service.UserService;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Override
    public String authenticateUser(String email, String password, Connection connection) {
        return this.userDAO.authenticateUser(email, password, connection);
    }

    @Override
    public User getUserById(int userID, Connection connection) {
        return this.userDAO.gerUserById(userID, connection);
    }

    @Override
    public List<User> getAllUsers(Connection connection) {
        return this.userDAO.getAllUsers(connection);
    }

    @Override
    public void saveUser(User user, Connection connection) {
        this.userDAO.saveUser(user, connection);
    }

    @Override
    public void updateUser(User user, Connection connection) {
        this.userDAO.updateUser(user, connection);
    }

    @Override
    public void deleteUser(int userID, Connection connection) {
        this.userDAO.deleteUser(userID, connection);
    }

    @Override
    public User login(String email, String password, Connection connection) {
        return this.userDAO.getUserByEmail(email, connection);
    }

    @Override
    public void assignCredits(int userID, int credits, Connection connection) {
        this.userDAO.assignCredits(userID, credits, connection);
    }
}
