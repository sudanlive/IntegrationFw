package com.app.intg.user.dto;

public class CreateUserResponse {
    private String userId;
    private String statusMessage;

    // Getters and Setters
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
