package com.scb.rest.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scb.rest.bookstore.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}