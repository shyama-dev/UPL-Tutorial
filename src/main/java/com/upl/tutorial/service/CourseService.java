package com.upl.tutorial.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upl.tutorial.dto.CourseRequest;
import com.upl.tutorial.dto.CourseResponse;
import com.upl.tutorial.exception.InstructorNotActiveException;
import com.upl.tutorial.model.Course;
import com.upl.tutorial.model.CourseStatus;
import com.upl.tutorial.model.User;
import com.upl.tutorial.model.UserStatus;
import com.upl.tutorial.repository.CourseRepo;
import com.upl.tutorial.repository.UserRepository;

@Service
public class CourseService {

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    UserRepository userRepo;

    public int create(CourseRequest request) {

        User instructor = userRepo.findById(request.getInstructorId()).orElseThrow(
            ()-> new InstructorNotActiveException("Instructor not approved for id "+request.getInstructorId())
        );
        if (instructor.getStatus().equals(UserStatus.Active)) {
            Course course = new Course();
            course.setInstructor(instructor);
            course.setDescription(request.getDescription());
            course.setStatus(CourseStatus.Active);
            course.setTitle(request.getTitle());
            course.setcreatedAt(LocalDateTime.now());
            Course savedCourse = courseRepo.save(course);
            System.out.println(" Saved Course :" + savedCourse);
            return savedCourse.getcourseId();

        } else {
            throw new InstructorNotActiveException("Instructor not "
            +"in active status for id :"+request.getInstructorId());

        }

    }

    public List<CourseResponse> courses(int instructor_id) {

        List<Course> courseList = courseRepo.findByInstructor_UserId(instructor_id);
        System.out.println(" Courses list :"+courseList);
        List<CourseResponse> responseList = courseList.stream()
                .map(course -> new CourseResponse(course.getcourseId(), course.getTitle(),
                 course.getDescription(),course.getInstructor().getuserId(), 
                 course.getStatus().name(),course.getcreatedAt())).toList();
        return responseList;
    }

}
