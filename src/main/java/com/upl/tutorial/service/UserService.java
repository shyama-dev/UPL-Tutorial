package com.upl.tutorial.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upl.tutorial.constants.UserConstants;
import com.upl.tutorial.dto.UserRequest;
import com.upl.tutorial.dto.UserResponse;
import com.upl.tutorial.model.User;
import com.upl.tutorial.model.UserRole;
import com.upl.tutorial.model.UserStatus;
import com.upl.tutorial.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public UserResponse register(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.Instructor);
        user.setStatus(UserStatus.Pending);
        user.setCreated_at(LocalDateTime.now());

        User userSaved = userRepo.save(user);
        System.out.println("userSaved  :" + userSaved);
        return new UserResponse(userSaved.getUser_id());

    }

    public UserResponse login(String email, String password) {
        UserResponse userResponse =new UserResponse();
        User user =userRepo.findByEmail(email);
        if(null==user) {
            userResponse.setResponseMessage(UserConstants.USER_NOT_FOUND);
            return userResponse;
        }
        userResponse.setRole(user.getRole().name());
        userResponse.setUserId(user.getUser_id());

        if(passwordEncoder.matches(password, user.getPassword())){

               userResponse.setResponseMessage(user.getStatus().name());
                           
        }else{
            userResponse.setResponseMessage(UserConstants.USER_INCORRECT_PASSWORD);
        }
        return userResponse;

    }



}
