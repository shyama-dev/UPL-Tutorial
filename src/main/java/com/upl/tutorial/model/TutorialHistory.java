package com.upl.tutorial.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
    private int history_id;

    @ManyToOne
    @JoinColumn(name = "tutorial_id", nullable = false)
    private Tutorial tutorial_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User instructor_id;

    private String changes;

    private LocalDateTime modified_at;

}
