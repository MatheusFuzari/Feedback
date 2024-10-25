package com.bosch.feedforward.exceptions;

public class UserException extends Exception{

    private final String code;

    public UserException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
