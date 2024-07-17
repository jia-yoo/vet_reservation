package com.example.veiwServer.filter;

public class UserNotApprovedException extends RuntimeException {
    public UserNotApprovedException(String message) {
        super(message);
    }
}
