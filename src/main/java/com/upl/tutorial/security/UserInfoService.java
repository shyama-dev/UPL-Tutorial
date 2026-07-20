package com.upl.tutorial.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.upl.tutorial.model.Users;
import com.upl.tutorial.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class UserInfoService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        System.out.println("********UserDetailsService *****************");
        // Wrap and return as Spring Security's UserDetails
        return new UserInfoDetails(user);
    }
    
}
