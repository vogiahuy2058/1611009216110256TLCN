package com.springboot.angular.coffeesystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class PaginationException extends RuntimeException {
    public PaginationException(String message) {
        super(message);
    }
}

