package com.fernst.entity;

/**
 * Created by fernando on 6/8/16.
 */
public class ApiResponse {
    String message;

    public ApiResponse() {}

    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
