package com.example.BookStoreApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BookStoreApp.model.Notify;

@Repository
public interface NotifyDAO extends JpaRepository<Notify, Integer> {
	List<Notify> findByUserProfileUsername(String username);
}
