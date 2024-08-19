package com.amrbsapp.controller;

import com.amrbsapp.entity.User;
import com.amrbsapp.service.UserService;
import com.amrbsapp.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class UserController {
    private UserService userService;
    private final Connection conn = DBConnection.getConnection();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    User getUser(int userID){
        return userService.getUserById(userID,conn);
    }
    List<User> getAllUsers(){
        return userService.getAllUsers(conn);
    }
    void addUser(User user){
        userService.saveUser(user,conn);
    }
    void updateUser(User user){
        userService.updateUser(user,conn);
    }
    void deleteUser(int userID){
        userService.deleteUser(userID,conn);
    }
    User login(String email, String password){
        return userService.login(email,password,conn);
    }
    void assignCredits(int userID, int credits){
        userService.assignCredits(userID,credits,conn);
    }
}
