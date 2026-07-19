package com.upl.tutorial.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upl.tutorial.constants.UserConstants;
import com.upl.tutorial.dto.CourseManageRequest;
import com.upl.tutorial.dto.CoursePageRequest;
import com.upl.tutorial.dto.CoursePageResponse;
import com.upl.tutorial.dto.CourseRequest;
import com.upl.tutorial.dto.CourseResponse;
import com.upl.tutorial.dto.InstructorResponse;
import com.upl.tutorial.exception.InstructorNotActiveException;
import com.upl.tutorial.exception.ResourceNotFoundException;
import com.upl.tutorial.model.Course;
import com.upl.tutorial.model.CourseHistory;
import com.upl.tutorial.model.CourseStatus;
import com.upl.tutorial.model.User;
import com.upl.tutorial.model.UserStatus;
import com.upl.tutorial.repository.CourseHistoryRepo;
import com.upl.tutorial.repository.CourseRepo;
import com.upl.tutorial.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Service
public class CourseService {

    private final CourseRepo courseRepo;

    private final UserRepository userRepo;

    private final CourseHistoryRepo courseHistoryRepo;

    public int createCourse(CourseRequest request) {

        User instructor = userRepo.findById(request.getInstructorId()).orElseThrow(
                () -> new ResourceNotFoundException("Instructor not found for id "
                        + request.getInstructorId()));
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
                    + "in active status for id :" + request.getInstructorId());

        }

    }

    public List<CourseResponse> getCoursesByInstructor(int instructor_id) {

        List<Course> courseList = courseRepo.findByInstructor_UserIdAndStatus(instructor_id,CourseStatus.Active);
        System.out.println(" Courses list :" + courseList);
        List<CourseResponse> responseList = courseList.stream()
                .map(course -> new CourseResponse(course.getcourseId(), course.getTitle(),
                        course.getDescription(), course.getInstructor().getuserId(),
                        course.getStatus().name(), course.getcreatedAt()))
                .toList();
        return responseList;
    }

    public Page<CoursePageResponse> getActiveCourses(CoursePageRequest request) {

        Sort sort = request.getSortDir().equalsIgnoreCase("desc")
                ? Sort.by(request.getSortBy()).descending()
                : Sort.by(request.getSortBy()).ascending();

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Page<Course> courseList = courseRepo.findAllByStatus(CourseStatus.Active, pageable);
        System.out.println(" Courses list :" + courseList);

        Page<CoursePageResponse> responsePage = courseList.map(course -> {
            CoursePageResponse dto = new CoursePageResponse();
            dto.setTitle(course.getTitle());
            dto.setDescription(course.getDescription());
            dto.setStatus(course.getStatus().name());

            if (course.getInstructor() != null) {
                InstructorResponse instructorDto = new InstructorResponse();
                instructorDto.setName(course.getInstructor().getName());
                instructorDto.setEmail(course.getInstructor().getEmail());
                instructorDto.setStatus(course.getInstructor().getStatus().name());
                dto.setInstructor(instructorDto);
            }
            return dto;
        });
        return responsePage;
    }

    @Transactional
    public void updateCourse(CourseManageRequest request) {
        Course course =courseRepo.findById(request.getCourseId())
        .orElseThrow(()->new ResourceNotFoundException(
            "Course is not present for course id:"+request.getCourseId()));
       
        User instructor = userRepo.findById(request.getInstructorId()).orElseThrow(
                () -> new ResourceNotFoundException("Instructor not found for id " 
                + request.getInstructorId()));
            

        if (null != request.getDescription() && !request.getDescription().isBlank())
        course.setDescription(request.getDescription());

        if (null != request.getTitle() && !request.getTitle().isBlank())
        course.setTitle(request.getTitle());
        
        CourseHistory courseHistory=new CourseHistory();
        courseHistory.setCourse(course);
        courseHistory.setInstructor(instructor);
        courseHistory.setChanges(request.getChanges());
        courseHistory.setmodifiedAt(LocalDateTime.now());
        courseHistoryRepo.save(courseHistory);

    }

    @Transactional
    public void deleteCourse(CourseManageRequest request) {

         Course course =courseRepo.findById(request.getCourseId())
        .orElseThrow(()->new ResourceNotFoundException(
            "Course is not present for course id:"+request.getCourseId()));
       
        User instructor = userRepo.findById(request.getInstructorId()).orElseThrow(
                () -> new ResourceNotFoundException("Instructor not found for id " 
                + request.getInstructorId()));

        course.setStatus(CourseStatus.Inactive);

        CourseHistory courseHistory=new CourseHistory();
        courseHistory.setCourse(course);
        courseHistory.setInstructor(instructor);
        courseHistory.setChanges(request.getChanges());
        courseHistory.setmodifiedAt(LocalDateTime.now());
        courseHistoryRepo.save(courseHistory);

    }

}
