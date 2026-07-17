package com.upl.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upl.tutorial.dto.InstructorApproveRequest;
import com.upl.tutorial.model.ApprovalStatus;
import com.upl.tutorial.model.UserStatus;
import com.upl.tutorial.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService service;

    @PostMapping("/approve")
    public ResponseEntity<String> approveInstructor(@RequestBody InstructorApproveRequest request) {
        request.setStatus(ApprovalStatus.Approved);
        service.approveInstructor(request, UserStatus.Active);
        return ResponseEntity.ok("Instructor Approved");
    }

    @PostMapping("/reject")
    public ResponseEntity<String> rejectInstructor(@RequestBody InstructorApproveRequest request) {
        request.setStatus(ApprovalStatus.Rejected);
        service.approveInstructor(request, UserStatus.Rejected);
        return ResponseEntity.ok("Instructor Rejected");
    }

}
