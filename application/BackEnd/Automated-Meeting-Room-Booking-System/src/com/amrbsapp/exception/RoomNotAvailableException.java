package com.amrbsapp.exception;

//when room not available in particular time slot

public class RoomNotAvailableException extends Exception {
    public RoomNotAvailableException(String message) {
        super(message);
    }
}
