package com.example.mongoservice.exception.leaveDb;

public class LeaveNotFoundException extends Exception{
    public LeaveNotFoundException(String message){
        super(message);
    }
}
