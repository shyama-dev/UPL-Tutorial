package com.upl.tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upl.tutorial.dto.LoginRequest;
import com.upl.tutorial.dto.UserRequest;
import com.upl.tutorial.dto.UserResponse;
import com.upl.tutorial.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("instructors")
@RequiredArgsConstructor
public class UserController {

    
    private final UserService service;

     @GetMapping("/")
     public String greet(){

        System.out.println("Heloooooo ");
        return "Heloooo ";
     }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request){
        UserResponse userResponse = service.register(request);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED); 

    }

    @PostMapping("/login")
     public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request){
        UserResponse userResponse=service.login(request.getEmail(), request.getPassword());
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
     }
    
}
