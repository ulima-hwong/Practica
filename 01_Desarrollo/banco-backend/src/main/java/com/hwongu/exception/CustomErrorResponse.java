package com.hwongu.exception;

import java.time.LocalDateTime;

public class CustomErrorResponse {

    private LocalDateTime datetime;
    private String message;
    private String path;

    public CustomErrorResponse() {
    }

    public CustomErrorResponse(LocalDateTime datetime, String message, String path) {
        this.datetime = datetime;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

