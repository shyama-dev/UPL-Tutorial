package com.upl.tutorial.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upl.tutorial.dto.TutorialRequest;
import com.upl.tutorial.dto.TutorialResponse;
import com.upl.tutorial.exception.ResourceNotFoundException;
import com.upl.tutorial.model.Course;
import com.upl.tutorial.model.Tutorial;
import com.upl.tutorial.repository.CourseRepo;
import com.upl.tutorial.repository.TutorialRepository;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepo;

    @Autowired
    CourseRepo courseRepo;

    public int create(TutorialRequest request) {
        Tutorial tutorial =new Tutorial();
        Course course =courseRepo.findById(request.getCourseId()).orElseThrow(()->
        new ResourceNotFoundException("Course not found for id :"+request.getCourseId()));
        tutorial.setCourse(course);
        tutorial.setTitle(request.getTitle());
        tutorial.setyoutubeLink(request.getYoutubeLink());
        tutorial.setContent(request.getContent());
        tutorial.setcreatedAt(LocalDateTime.now());
        Tutorial savedTutorial=tutorialRepo.save(tutorial);
        System.out.println(" Saved tutorial ::"+savedTutorial);
        return savedTutorial.gettutorialId();

    }

    public List<TutorialResponse> fetchTutorials(int courseId) {

        List<Tutorial> tutorials =tutorialRepo.findByCourse_CourseId(courseId);
        List<TutorialResponse> tutorialsList = tutorials.stream().map(tutorial -> 
            new TutorialResponse(tutorial.gettutorialId(),courseId,tutorial.getTitle(),
            tutorial.getContent(),tutorial.getyoutubeLink(),tutorial.getcreatedAt())).toList();
         System.out.println(" tutorialsList  ::"+tutorialsList);

        return tutorialsList;
    }
    
}
