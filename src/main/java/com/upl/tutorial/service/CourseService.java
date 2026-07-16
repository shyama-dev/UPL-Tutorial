package com.upl.tutorial.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

        Optional<User> instructor = userRepo.findById(request.getInstructor_id());
        if (instructor.isPresent() && instructor.get().getStatus().equals(UserStatus.Active)) {
            Course course = new Course();
            course.setInstructor(instructor.get());
            course.setDescription(request.getDescription());
            course.setStatus(CourseStatus.Active);
            course.setTitle(request.getTitle());
            course.setCreated_at(LocalDateTime.now());
            Course savedCourse = courseRepo.save(course);
            System.out.println(" Saved Course :" + savedCourse);
            return savedCourse.getCourse_id();

        } else {
            throw new InstructorNotActiveException("Instructor not approved");

        }

    }

    public List<CourseResponse> courses(int instructor_id) {

        List<Course> courseList = courseRepo.fetchCoursesByInstructor(instructor_id);
        System.out.println(" Courses list :"+courseList);
        List<CourseResponse> responseList = courseList.stream()
                .map(course -> new CourseResponse(course.getCourse_id(), course.getTitle(),
                 course.getDescription(),course.getInstructor().getUser_id(), 
                 course.getStatus().name(),course.getCreated_at())).toList();
        return responseList;
    }

}
