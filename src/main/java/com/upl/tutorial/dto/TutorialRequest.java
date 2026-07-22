package com.upl.tutorial.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorialRequest {
    
    @Min(value = 1, message = "Course ID must be at least 1")
     private int courseId;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Title is mandatory")
    private String content;

    private String youtubeLink;

    
}
