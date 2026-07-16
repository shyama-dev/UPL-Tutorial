package com.upl.tutorial.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class CourseResponse {
    
    private int courseId;
    private String title;    
    private String description;
    private int instructorId;
    private String status;
    private LocalDateTime createdAt;

       
}
