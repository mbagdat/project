package com.example.myproject.exception;

import com.example.myproject.util.CustomResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public CustomResponse<String> handleException(Exception ex) {
        CustomResponse<String> response = new CustomResponse<>("Internal server error", null, new Date());
        return response;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public CustomResponse<Void> handleUserNotFoundException(UserNotFoundException ex) {
        return  new CustomResponse<>("User not found", null, new Date());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public CustomResponse<Void> handleIllegalArgumentException(IllegalArgumentException ex) {
        CustomResponse<Void> response = new CustomResponse<>("Bad request", null, new Date());
        return response;
    }
}
