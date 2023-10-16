package com.project.userdept.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.userdept.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
