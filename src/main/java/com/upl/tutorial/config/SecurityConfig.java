package com.upl.tutorial.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder  passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
            // 1. Allow absolutely every URL without authentication
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            // 2. Disable the default login form completely
            .formLogin(form -> form.disable())
            // 3. Disable HTTP Basic authentication popup
            .httpBasic(basic -> basic.disable())
            // 4. Disable CSRF (usually needed if you are testing POST/PUT APIs)
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    

   
}
