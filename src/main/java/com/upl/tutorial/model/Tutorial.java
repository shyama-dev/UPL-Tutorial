package com.upl.tutorial.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tutorial_id")
    private int tutorialId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id" , nullable=false)
    private Course course;

    private String title;

    @Lob
    private String content;
    
    @Column(name = "youtube_link")
    private String youtubeLink;

    @Column(name = "created_at")
    private LocalDateTime createdAt;   

    public Tutorial() {
    }

    public Tutorial(int tutorialId, Course course, String title, String content, String youtubeLink,
            LocalDateTime createdAt) {
        this.tutorialId = tutorialId;
        this.course = course;
        this.title = title;
        this.content = content;
        this.youtubeLink = youtubeLink;
        this.createdAt = createdAt;
    }

    public int gettutorialId() {
        return tutorialId;
    }

    public void settutorialId(int tutorialId) {
        this.tutorialId = tutorialId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getyoutubeLink() {
        return youtubeLink;
    }

    public void setyoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public LocalDateTime getcreatedAt() {
        return createdAt;
    }

    public void setcreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Tutorial [tutorialId=" + tutorialId + ", course=" + course + ", title=" + title + ", content="
                + content + ", youtubeLink=" + youtubeLink + ", createdAt=" + createdAt + "]";
    }    
  
    
        

}
