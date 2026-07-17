package com.upl.tutorial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.upl.tutorial.model.User;
import com.upl.tutorial.model.UserStatus;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    User findByEmail(String email);

    @Modifying
    @Transactional 
    @Query("UPDATE User u SET u.status = :status WHERE u.userId = :userId")
    int updateStatusByUserId(@Param("userId") int userId, @Param("status") UserStatus status);
    
}
