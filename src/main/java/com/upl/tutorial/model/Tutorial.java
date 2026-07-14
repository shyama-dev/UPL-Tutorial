package com.upl.tutorial.model;

import java.time.LocalDateTime;

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
    private int tutorial_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_id" , nullable=false)
    private Course course;

    private String title;

    @Lob
    private String content;

    private String youtube_link;

    private LocalDateTime createed_at;   

    public Tutorial() {
    }

    public Tutorial(int tutorial_id, Course course, String title, String content, String youtube_link,
            LocalDateTime createed_at) {
        this.tutorial_id = tutorial_id;
        this.course = course;
        this.title = title;
        this.content = content;
        this.youtube_link = youtube_link;
        this.createed_at = createed_at;
    }

    public int getTutorial_id() {
        return tutorial_id;
    }

    public void setTutorial_id(int tutorial_id) {
        this.tutorial_id = tutorial_id;
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

    public String getYoutube_link() {
        return youtube_link;
    }

    public void setYoutube_link(String youtube_link) {
        this.youtube_link = youtube_link;
    }

    public LocalDateTime getCreateed_at() {
        return createed_at;
    }

    public void setCreateed_at(LocalDateTime createed_at) {
        this.createed_at = createed_at;
    }

    @Override
    public String toString() {
        return "Tutorial [tutorial_id=" + tutorial_id + ", course=" + course + ", title=" + title + ", content="
                + content + ", youtube_link=" + youtube_link + ", createed_at=" + createed_at + "]";
    }    
  
    
        

}
