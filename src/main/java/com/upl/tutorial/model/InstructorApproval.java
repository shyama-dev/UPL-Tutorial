package com.upl.tutorial.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="instructor_approval")
public class InstructorApproval {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int log_id;


    @OneToOne
    @JoinColumn(name="user_id" , nullable=false)
    private User user_id;
    
    /* 
    @ManyToOne
    @JoinColumn(name="user_id" , nullable=false)
    private User admin_id;*/

    private ApprovalStatus status;

    @Lob
    private String remarks;

    private LocalDateTime timestamp;
    
} 

enum ApprovalStatus{

    Approved, 
    Rejected
}
