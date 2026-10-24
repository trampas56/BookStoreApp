package com.example.BookStoreApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookStoreApp.dao.BookAuthorDAO;
import com.example.BookStoreApp.model.BookAuthor;

import jakarta.transaction.Transactional;

@Service
public class BookAuthorServiceImpl implements BookAuthorService {
	
	@Autowired
	BookAuthorDAO bookAuthorDAO;
	

	@Override
	public void saveAuthor(BookAuthor bookAuthor){
		bookAuthorDAO.save(bookAuthor);		
	}
	
	@Transactional
    public BookAuthor findByName(String name) {
        Optional<BookAuthor> optionalBookAuthor = bookAuthorDAO.findByName(name);
        return optionalBookAuthor.orElse(null); // Return null if not found, you can adjust this behavior as needed
    }

}
