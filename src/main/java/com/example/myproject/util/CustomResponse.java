package com.example.myproject.util;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CustomResponse <T> implements Serializable {
    private String message;
    private T data;
    private Date date;

    public CustomResponse(String message, T data, Date date) {
        this.message = message;
        this.data = data;
        this.date = date;
    }

// getters and setters

}
