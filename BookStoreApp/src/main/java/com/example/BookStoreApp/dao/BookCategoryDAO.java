package com.example.BookStoreApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BookStoreApp.model.BookCategory;

@Repository
public interface BookCategoryDAO extends JpaRepository<BookCategory, Integer> {
	List<BookCategory> findByName(String name);
}
