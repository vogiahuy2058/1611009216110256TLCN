package com.springboot.angular.coffeesystem.configuration;

import com.springboot.angular.coffeesystem.dto.ResponseDto;
import com.springboot.angular.coffeesystem.exception.ExistException;
import com.springboot.angular.coffeesystem.exception.FileStorageException;
import com.springboot.angular.coffeesystem.exception.NotFoundException;
import com.springboot.angular.coffeesystem.exception.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@ControllerAdvice("com.springboot.angular.coffeesystem")
public class ExceptionController {
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseDto> exception(NotFoundException exception){
        return new ResponseEntity<>(new ResponseDto(HttpStatus.NOT_FOUND.value(),exception.getMessage(),null), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = ExistException.class)
    public ResponseEntity<ResponseDto> exception(ExistException exception){
        return new ResponseEntity<>(new ResponseDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage(),null), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = ParseException.class)
    public ResponseEntity<ResponseDto> exception(ParseException exception){
        return new ResponseEntity<>(new ResponseDto(HttpStatus.SERVICE_UNAVAILABLE.value(), exception.getMessage(),null), HttpStatus.SERVICE_UNAVAILABLE);
    }
    @ExceptionHandler(value = FileStorageException.class)
    public ResponseEntity<ResponseDto> exception(FileNotFoundException exception){
        return new ResponseEntity<>(new ResponseDto(HttpStatus.EXPECTATION_FAILED.value(), exception.getMessage(),null), HttpStatus.EXPECTATION_FAILED);
    }
}
