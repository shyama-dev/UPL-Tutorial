package com.upl.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upl.tutorial.model.CourseHistory;

@Repository
public interface CourseHistoryRepo extends JpaRepository<CourseHistory,Integer> {
    
}
