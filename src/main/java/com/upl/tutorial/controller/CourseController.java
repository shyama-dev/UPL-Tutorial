package com.upl.tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upl.tutorial.dto.CourseRequest;
import com.upl.tutorial.dto.CourseResponse;
import com.upl.tutorial.service.CourseService;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired 
    CourseService service;

     @GetMapping
     public ResponseEntity<List<CourseResponse>> courses(@RequestParam int instructorId){
      List<CourseResponse> courses =service.courses(instructorId);

      return ResponseEntity.status(HttpStatus.OK).body(courses);
   
     }

     @PostMapping("/create")
     public ResponseEntity<Integer> create(@RequestBody CourseRequest request){
        System.out.println("**CourseRequest**** :"+request);
        int courseId =service.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(Integer.valueOf(courseId));        
     }
    
}
