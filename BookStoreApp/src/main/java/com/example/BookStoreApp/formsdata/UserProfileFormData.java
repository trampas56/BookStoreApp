package com.example.BookStoreApp.formsdata;

import java.util.List;

public class UserProfileFormData {
	private String username;
	
	private String address;
	
	private int age;
	
	private String phoneNumber;
	
	private String favouriteBookAuthors;
	
	private List<Integer> favouriteBookCategories;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFavouriteBookAuthors() {
		return favouriteBookAuthors;
	}

	public void setFavouriteBookAuthors(String favouriteBookAuthors) {
		this.favouriteBookAuthors = favouriteBookAuthors;
	}

	public List<Integer> getFavouriteBookCategories() {
		return favouriteBookCategories;
	}

	public void setFavouriteBookCategories(List<Integer> favouriteBookCategories) {
		this.favouriteBookCategories = favouriteBookCategories;
	}
}
