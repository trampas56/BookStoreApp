package com.example.BookStoreApp.strategies;

import java.util.ArrayList;

import com.example.BookStoreApp.dao.BookDao;
import com.example.BookStoreApp.formsdata.BookFormData;
import com.example.BookStoreApp.formsdata.SearchFormData;
import com.example.BookStoreApp.model.UserProfile;

public interface SearchStrategy {
	ArrayList<BookFormData> search(SearchFormData bookFormData, BookDao bookDao, UserProfile currentUser);
}
