package com.upl.tutorial.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upl.tutorial.constants.UserConstants;
import com.upl.tutorial.dto.InstructorApproveRequest;
import com.upl.tutorial.dto.UserRequest;
import com.upl.tutorial.dto.UserResponse;
import com.upl.tutorial.exception.EntityNotFoundException;
import com.upl.tutorial.model.InstructorApproval;
import com.upl.tutorial.model.Users;
import com.upl.tutorial.model.UserRole;
import com.upl.tutorial.model.UserStatus;
import com.upl.tutorial.repository.InstructorApprovalRepo;
import com.upl.tutorial.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepo;

    private final InstructorApprovalRepo repo;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse register(UserRequest request) {
        Users user = new Users();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.Instructor);
        user.setStatus(UserStatus.Pending);
        user.setcreatedAt(LocalDateTime.now());

        Users userSaved = userRepo.save(user);
        System.out.println("userSaved  :" + userSaved);
        return new UserResponse(userSaved.getuserId());

    }

/*     public UserResponse login(String email, String password) {
        UserResponse userResponse = new UserResponse();
        Users user;// = userRepo.findByEmail(email);
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

    } */
 
    @Transactional
    public void approveInstructor(InstructorApproveRequest request, UserStatus status) {
       
        List<Integer> userIdList =List.of(request.getUserId(),request.getAdminId());
        List<Users> usersList=userRepo.findAllById(userIdList);
        
        Users instructor =usersList.stream()
        .filter(user->"Instructor".equalsIgnoreCase(user.getRole().name()))
        .findFirst().orElseThrow(()->new EntityNotFoundException(
            "Instructor Not found for id :"+request.getUserId()));

         Users admin =usersList.stream()
        .filter(user->"Admin".equalsIgnoreCase(user.getRole().name()))
        .findFirst().orElseThrow(()->new EntityNotFoundException(
            "Admin Not found for id :"+request.getAdminId()));      
        
         //updating status to approved   
        instructor.setStatus(status);

        InstructorApproval approvalrequest=new InstructorApproval();
        approvalrequest.setInstructor(instructor);
        approvalrequest.setAdmin(admin);
        approvalrequest.setRemarks(request.getRemarks());
        approvalrequest.setStatus(request.getStatus());
        approvalrequest.setTimestamp(LocalDateTime.now());
        repo.save(approvalrequest);
                
    }

}
