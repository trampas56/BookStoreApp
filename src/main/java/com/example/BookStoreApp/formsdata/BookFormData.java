package com.example.BookStoreApp.formsdata;

public class BookFormData {
	private int bookId;

	private String title;

	private String summary;

	private String userProfile;
	
	private int categoryId;
	
	private String categoryName;

	private String bookAuthors;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public String getBookAuthors() {
		return bookAuthors;
	}

	public void setBookAuthors(String bookAuthors) {
		this.bookAuthors = bookAuthors;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

}
