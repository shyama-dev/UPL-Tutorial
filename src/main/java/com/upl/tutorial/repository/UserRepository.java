package com.upl.tutorial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upl.tutorial.model.Users;
import com.upl.tutorial.model.UserStatus;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>{

    Optional<Users> findByEmail(String email);

     
}
