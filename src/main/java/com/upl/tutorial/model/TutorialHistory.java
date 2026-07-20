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
@Table(name = "tutorial_history")
public class TutorialHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private int historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutorial_id", nullable = false)
    private Tutorial tutorial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private Users instructor;

    private String changes;
    
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    
    public TutorialHistory() {
    }
    
    public TutorialHistory(int historyId, Tutorial tutorial, Users instructor, String changes,
            LocalDateTime modifiedAt) {
        this.historyId = historyId;
        this.tutorial = tutorial;
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

    public Tutorial getTutorial() {
        return tutorial;
    }

    public void setTutorial(Tutorial tutorial) {
        this.tutorial = tutorial;
    }

    public Users getInstructor() {
        return instructor;
    }

    public void setInstructor(Users instructor) {
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
        return "TutorialHistory [historyId=" + historyId + ", tutorial=" + tutorial + ", instructor=" + instructor
                + ", changes=" + changes + ", modifiedAt=" + modifiedAt + "]";
    }

    

}
