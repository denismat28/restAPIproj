package com.task.restAPIproj.exception;

public class OrderAlreadyExistException extends Exception{
    public OrderAlreadyExistException(String message) {
        super(message);
    }
}
