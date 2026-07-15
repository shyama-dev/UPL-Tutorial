package com.upl.tutorial.dto;

public class UserResponse {

    private int userId;
    private String responseMessage;
    private String role;
    

    public UserResponse() {

    }    
    
    public UserResponse(int userId) {
        this.userId = userId;
    }

    
    public UserResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public UserResponse(int userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    
    @Override
    public String toString() {
        return "UserResponse [userId=" + userId + ", responseMessage=" + responseMessage + "]";
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
    
}
