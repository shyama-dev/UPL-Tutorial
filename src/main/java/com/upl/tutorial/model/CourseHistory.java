package com.upl.tutorial.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_history")
public class CourseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private int historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    private String changes;
    
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public CourseHistory() {
    }

    public CourseHistory(int historyId, Course course, User instructor, String changes, LocalDateTime modifiedAt) {
        this.historyId = historyId;
        this.course = course;
        this.instructor = instructor;
        this.changes = changes;
        this.modifiedAt = modifiedAt;
    }

    public int gethistoryId() {
        return historyId;
    }

    public void sethistoryId(int historyId) {
        this.historyId = historyId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }

    public LocalDateTime getmodifiedAt() {
        return modifiedAt;
    }

    public void setmodifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "CourseHistory [historyId=" + historyId + ", course=" + course + ", instructor=" + instructor
                + ", changes=" + changes + ", modifiedAt=" + modifiedAt + "]";
    }

    
    

}
