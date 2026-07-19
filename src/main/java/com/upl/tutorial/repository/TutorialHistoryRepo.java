package com.upl.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upl.tutorial.model.TutorialHistory;

@Repository
public interface TutorialHistoryRepo extends  JpaRepository<TutorialHistory,Integer>{
    
    
} 
