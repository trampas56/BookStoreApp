package com.example.BookStoreApp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BookStoreApp.model.UserProfile;

public interface UserProfileDAO extends JpaRepository<UserProfile, Integer>{
	Optional<UserProfile> findByUsername(String username);
}
