package com.upl.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upl.tutorial.model.InstructorApproval;

@Repository
public interface InstructorApprovalRepo extends JpaRepository<InstructorApproval,Integer>{
    
}
