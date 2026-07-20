package com.upl.tutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upl.tutorial.model.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial,Integer> {


    List<Tutorial> findByCourse_CourseId(int course_id);

   
}
