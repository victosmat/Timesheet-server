package com.timesheet.dto;

import org.springframework.http.HttpStatus;

public class Message<T> {
    private String message;
    private T object;

    public Message(String message, T object) {
        this.message = message;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
