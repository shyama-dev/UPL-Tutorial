package com.upl.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upl.tutorial.model.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial,Integer> {
    
}
