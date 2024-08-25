package com.amrbsapp.controller;

import com.amrbsapp.dao.impl.*;
import com.amrbsapp.service.impl.*;

public class ControllerProvider {
    public static RoomController getRoomController() {
        return new RoomController(new RoomServiceImpl(new RoomImpl()));
    }

    public static UserController getUserController() {
        return new UserController(new UserServiceImpl(new UserImpl()));
    }

    public static BookingController getBookingController() {
        return new BookingController(new BookingServiceImpl(new BookingImpl()));
    }

    public static MeetingController getMeetingController() {
        return new MeetingController(new MeetingServiceImpl(new MeetingImpl()));
    }

    public static AmenityController getAmenityController() {
        return new AmenityController(new AmenityServiceImpl(new AmenityImpl()));
    }
}
