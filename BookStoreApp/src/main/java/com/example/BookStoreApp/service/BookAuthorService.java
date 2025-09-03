package com.example.BookStoreApp.service;

import org.springframework.stereotype.Service;

import com.example.BookStoreApp.model.BookAuthor;

@Service
public interface BookAuthorService {
	
	public void saveAuthor(BookAuthor bookAuthor);
	
	public BookAuthor findByName(String name);

}
