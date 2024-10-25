package com.bosch.feedforward.exceptions;

public class UserNotFoundException extends UserException{

    public UserNotFoundException(){
        super("User not found!", "USER-NOT-FOUND");
    }

    public UserNotFoundException(String message, String code) {
        super(message, code);
    }
}
