package com.upl.tutorial.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private Users instructor;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_id" , nullable=false)
    private Users admin;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus status;

    @Lob
    private String remarks;

    private LocalDateTime timestamp;

    public InstructorApproval() {
    }

    public InstructorApproval(int logId, Users instructor, Users admin, ApprovalStatus status, String remarks,
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

    public Users getInstructor() {
        return instructor;
    }

    public void setInstructor(Users instructor) {
        this.instructor = instructor;
    }

    public Users getAdmin() {
        return admin;
    }

    public void setAdmin(Users admin) {
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

