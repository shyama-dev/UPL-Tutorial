package com.upl.tutorial.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
// @RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JwtFilter jwtAuthFilter;

    public SecurityConfig(UserDetailsService userDetailsService, JwtFilter jwtAuthFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 4. Disable CSRF (usually needed if you are testing POST/PUT APIs)
                .csrf(csrf -> csrf.disable()
                        // 1. Allow absolutely every URL without authentication
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/instructors/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/courses").permitAll()
                                .anyRequest().authenticated())
                        // 2. Disable the default login form completely
                        .formLogin(form -> form.disable())
                        // 3. Disable HTTP Basic authentication popup
                        // .httpBasic(basic -> basic.disable())
                        .httpBasic(Customizer.withDefaults())
                        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authenticationProvider(authenticationProvider())
                        // Add JWT filter before Spring Security's default filter
                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                );

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider(userDetailsService);
        daoAuthProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthProvider;
    }

    @Bean
    public AuthenticationManager authenticationmanager(AuthenticationConfiguration config) {
        return config.getAuthenticationManager();
    }

}
