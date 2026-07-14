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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="instructor_approval")
public class InstructorApproval {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int log_id;


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

    public InstructorApproval(int log_id, User instructor, User admin, ApprovalStatus status, String remarks,
            LocalDateTime timestamp) {
        this.log_id = log_id;
        this.instructor = instructor;
        this.admin = admin;
        this.status = status;
        this.remarks = remarks;
        this.timestamp = timestamp;
    }

    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
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
        return "InstructorApproval [log_id=" + log_id + ", instructor=" + instructor + ", admin=" + admin + ", status="
                + status + ", remarks=" + remarks + ", timestamp=" + timestamp + "]";
    }

    
    
} 

enum ApprovalStatus{

    Approved, 
    Rejected
}
