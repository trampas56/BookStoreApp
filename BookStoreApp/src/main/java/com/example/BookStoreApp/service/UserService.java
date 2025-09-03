package com.example.BookStoreApp.service;

import org.springframework.stereotype.Service;

import com.example.BookStoreApp.model.User;

@Service
public interface UserService {
	public void saveUser(User user);
    public boolean isUserPresent(User user);
}