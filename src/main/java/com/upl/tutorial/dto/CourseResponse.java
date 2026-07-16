package com.upl.tutorial.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
public class CourseResponse {
    
    private int course_id;
    private String title;    
    private String description;
    private int instructor_id;
    private String status;
    private LocalDateTime created_at;

       
}
