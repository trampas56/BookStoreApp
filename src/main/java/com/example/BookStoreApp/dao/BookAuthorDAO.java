package com.example.BookStoreApp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BookStoreApp.model.BookAuthor;

@Repository
public interface BookAuthorDAO extends JpaRepository<BookAuthor, Integer>{
	Optional<BookAuthor> findByName(String name);
}
