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
import jakarta.persistence.Table;

@Entity
@Table(name="instructor_approval")
public class InstructorApproval {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private int logId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="instructor_id" , nullable=false)
    private User instructor;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id" , nullable=false)
    private User admin;

    private ApprovalStatus status;

    @Lob
    private String remarks;

    private LocalDateTime timestamp;

    public InstructorApproval() {
    }

    public InstructorApproval(int logId, User instructor, User admin, ApprovalStatus status, String remarks,
            LocalDateTime timestamp) {
        this.logId = logId;
        this.instructor = instructor;
        this.admin = admin;
        this.status = status;
        this.remarks = remarks;
        this.timestamp = timestamp;
    }

    public int getlogId() {
        return logId;
    }

    public void setlogId(int logId) {
        this.logId = logId;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public ApprovalStatus getStatus() {
        return status;
    }

    public void setStatus(ApprovalStatus status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "InstructorApproval [logId=" + logId + ", instructor=" + instructor + ", admin=" + admin + ", status="
                + status + ", remarks=" + remarks + ", timestamp=" + timestamp + "]";
    }

        
} 

