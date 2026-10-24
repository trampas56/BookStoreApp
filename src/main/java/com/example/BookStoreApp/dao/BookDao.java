package com.example.BookStoreApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BookStoreApp.model.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {
	List<Book> findByTitle(String title);
	List<Book> findByTitleContaining(String title);
	List<Book> findByUserProfileUsernameNot(String username);
}
