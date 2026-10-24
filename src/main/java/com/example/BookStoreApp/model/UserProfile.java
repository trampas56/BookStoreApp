package com.example.BookStoreApp.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="profiles")
public class UserProfile {
	
	@Id
	@Column(name="user_name", unique= true)
	private String username;
	
	@Column(name="address")
	private String address;
	
	@Column(name="age")
	private int age;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	@OneToMany(mappedBy="userProfile", fetch = FetchType.EAGER)
	List<Book> bookOffers;
	
	@ManyToMany(mappedBy="requestingUsers")
	List<Book> requestedBooks;
	
	@ManyToMany
	@JoinTable(
	    name = "user_favourite_authors",
	    joinColumns = @JoinColumn(name = "user_name", referencedColumnName = "user_name"),
	    inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "author_id")
	)
	List<BookAuthor> favouriteBookAuthors;

	@ManyToMany
    @JoinTable(
        name = "user_favourite_categories",
        joinColumns = @JoinColumn(name = "user_name", referencedColumnName = "user_name"), 
        inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    )
	List<BookCategory> favouriteBookCategories;
	
	@OneToMany(mappedBy="userProfile", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	List<Notify> notifications;
	
	public List<Book> getBookOffers() {
		return bookOffers;
	}

	public List<Book> getRequestedBooks() {
		return requestedBooks;
	}

	public void setRequestedBooks(List<Book> requestedBooks) {
		this.requestedBooks = requestedBooks;
	}

	public void setBookOffers(List<Book> bookOffers) {
		this.bookOffers = bookOffers;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
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
	
	public List<BookAuthor> getFavouriteBookAuthors() {
		return favouriteBookAuthors;
	}

	public void setFavouriteBookAuthors(List<BookAuthor> favouriteBookAuthors) {
		this.favouriteBookAuthors = favouriteBookAuthors;
	}
	
	public String toString() {
		return username + ", " + address;
	}

	public List<BookCategory> getFavouriteBookCategories() {
		return favouriteBookCategories;
	}

	public void setFavouriteBookCategories(List<BookCategory> favouriteBookCategories) {
		this.favouriteBookCategories = favouriteBookCategories;
	}

	public List<Notify> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notify> notifications) {
		this.notifications = notifications;
	}

}
