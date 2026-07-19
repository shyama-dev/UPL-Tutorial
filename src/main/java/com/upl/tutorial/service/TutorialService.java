package com.upl.tutorial.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upl.tutorial.dto.TutorialManageRequest;
import com.upl.tutorial.dto.TutorialRequest;
import com.upl.tutorial.dto.TutorialResponse;
import com.upl.tutorial.exception.ResourceNotFoundException;
import com.upl.tutorial.model.Course;
import com.upl.tutorial.model.Tutorial;
import com.upl.tutorial.model.TutorialHistory;
import com.upl.tutorial.model.User;
import com.upl.tutorial.repository.CourseRepo;
import com.upl.tutorial.repository.TutorialHistoryRepo;
import com.upl.tutorial.repository.TutorialRepository;
import com.upl.tutorial.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TutorialService {

    private final TutorialRepository tutorialRepo;

    private final CourseRepo courseRepo;

     private final UserRepository userRepo;

     private final TutorialHistoryRepo tutorialHistoryRepo;



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

    @Transactional
    public void updateTutorial(TutorialManageRequest request) {

    Tutorial tutorial =tutorialRepo.findById(request.getTutorialId()).orElseThrow(()->
        new ResourceNotFoundException("Tutorial not found for id :"+request.getTutorialId()));
        
        if (null != request.getContent() && !request.getContent().isBlank())
        tutorial.setContent(request.getContent());

        if (null != request.getTitle() && !request.getTitle().isBlank())
            tutorial.setTitle(request.getTitle());

        if (null != request.getYoutubeLink() && !request.getYoutubeLink().isBlank())
            tutorial.setyoutubeLink(request.getYoutubeLink());

         User user =userRepo.findById(request.getInstructorId()).orElseThrow(()->
        new ResourceNotFoundException("Instructor not found for id :"+request.getInstructorId()));

        TutorialHistory tutorialHistory= new TutorialHistory();
        tutorialHistory.setTutorial(tutorial);
        tutorialHistory.setInstructor(user);
        tutorialHistory.setChanges(request.getChanges());
        tutorialHistory.setmodifiedAt(LocalDateTime.now());
        tutorialHistoryRepo.save(tutorialHistory);
    }

    
    
}
