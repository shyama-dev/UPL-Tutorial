package com.upl.tutorial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.upl.tutorial.dto.TutorialRequest;
import com.upl.tutorial.dto.TutorialResponse;
import com.upl.tutorial.service.TutorialService;

@RestController
@RequestMapping("/tutorials")
public class TutorialController {

    @Autowired
    TutorialService service;

    @GetMapping
    public ResponseEntity<List<TutorialResponse>> tutorials(@RequestParam int courseId){

        List<TutorialResponse> tutorials = service.fetchTutorials(courseId);
        return ResponseEntity.ok(tutorials);
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> create(@RequestBody TutorialRequest request){

        int tutorialId = service.create(request);
        return ResponseEntity.ok(tutorialId);

    }
    
}
