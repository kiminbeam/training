package com.example.restServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restServer.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
