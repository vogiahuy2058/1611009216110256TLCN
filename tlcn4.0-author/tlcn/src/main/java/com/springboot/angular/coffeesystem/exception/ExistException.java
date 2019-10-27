package com.springboot.angular.coffeesystem.exception;

public class ExistException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ExistException(String message) {
        super(message);
    }
}