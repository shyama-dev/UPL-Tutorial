package com.upl.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upl.tutorial.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    User findByEmail(String email);
    
}
