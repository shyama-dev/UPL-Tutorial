package com.upl.tutorial.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upl.tutorial.constants.UserConstants;
import com.upl.tutorial.dto.InstructorApproveRequest;
import com.upl.tutorial.dto.UserRequest;
import com.upl.tutorial.dto.UserResponse;
import com.upl.tutorial.exception.ResourceNotFoundException;
import com.upl.tutorial.model.InstructorApproval;
import com.upl.tutorial.model.User;
import com.upl.tutorial.model.UserRole;
import com.upl.tutorial.model.UserStatus;
import com.upl.tutorial.repository.InstructorApprovalRepo;
import com.upl.tutorial.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    InstructorApprovalRepo repo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public UserResponse register(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.Instructor);
        user.setStatus(UserStatus.Pending);
        user.setcreatedAt(LocalDateTime.now());

        User userSaved = userRepo.save(user);
        System.out.println("userSaved  :" + userSaved);
        return new UserResponse(userSaved.getuserId());

    }

    public UserResponse login(String email, String password) {
        UserResponse userResponse = new UserResponse();
        User user = userRepo.findByEmail(email);
        if (null == user) {
            userResponse.setResponseMessage(UserConstants.USER_NOT_FOUND);
            return userResponse;
        }
        userResponse.setRole(user.getRole().name());
        userResponse.setUserId(user.getuserId());

        if (passwordEncoder.matches(password, user.getPassword())) {

            userResponse.setResponseMessage(user.getStatus().name());

        } else {
            userResponse.setResponseMessage(UserConstants.USER_INCORRECT_PASSWORD);
        }
        return userResponse;

    }

    public void approveInstructor(InstructorApproveRequest request, UserStatus status) {
        int rowsModified =userRepo.updateStatusByUserId(request.getUserId(),status);
        if(rowsModified == 0)
            throw new ResourceNotFoundException("Instructor Not Found for this id :"+request.getUserId());
        InstructorApproval approvalrequest=new InstructorApproval();
        List<Integer> userIdList =List.of(request.getUserId(),request.getAdminId());
        List<User> usersList=userRepo.findAllById(userIdList);
        
        User instructor =usersList.stream()
        .filter(user->"Instructor".equalsIgnoreCase(user.getRole().name()))
        .findFirst().orElseThrow(()->new ResourceNotFoundException(
            "Instructor Not found for id :"+request.getUserId()));

         User admin =usersList.stream()
        .filter(user->"Admin".equalsIgnoreCase(user.getRole().name()))
        .findFirst().orElseThrow(()->new ResourceNotFoundException(
            "Admin Not found for id :"+request.getAdminId()));        
        
        approvalrequest.setInstructor(instructor);
        approvalrequest.setAdmin(admin);
        approvalrequest.setRemarks(request.getRemarks());
        approvalrequest.setStatus(request.getStatus());
        approvalrequest.setTimestamp(LocalDateTime.now());
        repo.save(approvalrequest);
                
    }

}
