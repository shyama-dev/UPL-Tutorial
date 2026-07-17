package com.upl.tutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.upl.tutorial.model.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial,Integer> {

   // @Query(value = "SELECT * FROM tutorial WHERE course_id = :course_id", nativeQuery = true)
   //    List<Tutorial> findByCourseId(@Param("course_id")int course_id);

    List<Tutorial> findByCourse_CourseId(int course_id);

    // @Query("Select t from tutorial t where t.course.course_id = :course_id")


   
}
