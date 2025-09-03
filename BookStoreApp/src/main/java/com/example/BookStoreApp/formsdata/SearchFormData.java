package com.example.BookStoreApp.formsdata;

import java.util.List;

public class SearchFormData {
	private int strategy;
	
	private String title;
	
	private List<Integer> bookAuthorsId;

	public int getStrategy() {
		return strategy;
	}

	public void setStrategy(int strategy) {
		this.strategy = strategy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Integer> getBookAuthorsId() {
		return bookAuthorsId;
	}

	public void setBookAuthorsId(List<Integer> bookAuthorsId) {
		this.bookAuthorsId = bookAuthorsId;
	}
}
