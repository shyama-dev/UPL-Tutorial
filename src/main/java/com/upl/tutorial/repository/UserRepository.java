package com.upl.tutorial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upl.tutorial.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>{

    Optional<Users> findByEmail(String email);

     
}
