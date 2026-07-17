package com.upl.tutorial.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upl.tutorial.model.Course;
import com.upl.tutorial.model.CourseStatus;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {

 
List<Course> findByInstructor_UserId( int instructor_id);  

Page<Course> findAllByStatus(CourseStatus status, Pageable pageable);



}
