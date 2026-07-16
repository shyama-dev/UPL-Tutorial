package com.upl.tutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upl.tutorial.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {

//@Query("SELECT c FROM Course c WHERE c.instructor.user_id = :instructor_id")
//List<Course> fetchCoursesByInstructorId(@Param("instructor_id") int instructor_id);  
List<Course> findByInstructor_userId( int instructor_id);  



}
