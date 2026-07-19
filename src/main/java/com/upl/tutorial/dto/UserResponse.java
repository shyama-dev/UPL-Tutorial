package com.upl.tutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private int userId;
    private String responseMessage;
    private String role;
    
    public UserResponse(int userId) {
        this.userId = userId;
    }

    
    
    
}
