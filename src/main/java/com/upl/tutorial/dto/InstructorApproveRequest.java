package com.upl.tutorial.dto;

import com.upl.tutorial.model.ApprovalStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorApproveRequest {

    @NotNull(message = "Instructor ID is required")
    private int userId;

    @NotNull(message = "Admin ID is required")
    private int adminId;

    private ApprovalStatus status;

    @Size(max = 1000, message = "Remarks cannot exceed 1000 characters")
    private String remarks;
    
}
