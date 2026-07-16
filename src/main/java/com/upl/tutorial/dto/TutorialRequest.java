package com.upl.tutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorialRequest {

     private int courseId;

    private String title;
    
    private String content;

    private String youtubeLink;

    
}
