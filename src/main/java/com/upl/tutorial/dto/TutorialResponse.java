package com.upl.tutorial.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorialResponse {

    private int tutorialId;
    private int courseId;
    private String title;
    private String content;

    private String youtubeLink;
    private LocalDateTime createdAt;
}
