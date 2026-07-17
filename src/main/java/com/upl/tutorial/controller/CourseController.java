package com.upl.tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upl.tutorial.dto.CoursePageRequest;
import com.upl.tutorial.dto.CoursePageResponse;
import com.upl.tutorial.dto.CourseRequest;
import com.upl.tutorial.dto.CourseResponse;
import com.upl.tutorial.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired 
    CourseService service;

    @GetMapping
    public ResponseEntity<Page<CoursePageResponse>> getActiveCourses(CoursePageRequest request){
      Page<CoursePageResponse> courses =service.getActiveCourses(request);

      return ResponseEntity.status(HttpStatus.OK).body(courses);
   
     }

     @GetMapping("/instructor")
     public ResponseEntity<List<CourseResponse>> getCoursesByInstructor(@RequestParam int instructorId){
      List<CourseResponse> courses =service.getCoursesByInstructor(instructorId);

      return ResponseEntity.status(HttpStatus.OK).body(courses);
   
     }

     @PostMapping("/create")
     public ResponseEntity<Integer> createCourse(@RequestBody CourseRequest request){
        System.out.println("**CourseRequest**** :"+request);
        int courseId =service.createCourse(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(Integer.valueOf(courseId));        
     }
    
}
