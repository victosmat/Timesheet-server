package com.timesheet.dto.error;

import java.io.Serializable;
import java.util.Date;

public class ErrorMessage implements Serializable {
    private int statusCode;
    private Date timestamp;
    private String message;
    private String description;

    public ErrorMessage() {
    }

    public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
