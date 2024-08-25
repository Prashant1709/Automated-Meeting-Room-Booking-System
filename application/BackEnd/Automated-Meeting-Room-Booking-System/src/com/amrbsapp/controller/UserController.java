package com.amrbsapp.controller;

import com.amrbsapp.entity.User;
import com.amrbsapp.service.UserService;
import com.amrbsapp.util.DBConnection;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class UserController {
    private UserService userService;
    private final Connection conn = DBConnection.getConnection();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public boolean checkMondayAnd6PM(){
        LocalDateTime now = LocalDateTime.now();
        boolean test = false;
        if (now.getDayOfWeek().toString().equals("MONDAY") && now.getHour() == 6){
            test = true;
        }
        return test;
    }

    public String authenticateUser(String email, String password){
        return userService.authenticateUser(email,password,conn);
    }
    public User getUser(int userID){
        return userService.getUserById(userID,conn);
    }
    public List<User> getAllUsers(){
        return userService.getAllUsers(conn);
    }
    public void addUser(User user){
        userService.saveUser(user,conn);
    }
    public void updateUser(User user){
        userService.updateUser(user,conn);
    }
    public void deleteUser(int userID){
        userService.deleteUser(userID,conn);
    }
    public User login(String email, String password){
        return userService.login(email,password,conn);
    }
    public void assignCredits(int userID, int credits){
        userService.assignCredits(userID,credits,conn);
    }
}
