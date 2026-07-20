package com.upl.tutorial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.upl.tutorial.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
// @RequiredArgsConstructor
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public String login(String username, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        System.out.println("**************Authenticated***********" + authentication.isAuthenticated());

        if (authentication.isAuthenticated()) {
            System.out.println("**************Authenticated***********");
            String token =jwtService.generateToken(username);
            System.out.println("**************token********  : "+token);
            return token;
        } else {
            return "Authentication failed";
        }

    }

}
