package com.example.BookStoreApp.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="book_id")
	private int bookId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="summary")
	private String summary;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userProfile_id")
	private UserProfile userProfile;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_wrote",
            joinColumns = @JoinColumn(
                    name = "book_id", referencedColumnName = "book_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "author_id", referencedColumnName = "author_id"
            )
    )
	private List<BookAuthor> bookAuthors = new ArrayList<BookAuthor>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="category_id")
	private BookCategory BookCategory;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name = "requested_books",
            joinColumns = @JoinColumn(
                    name = "book_id", referencedColumnName = "book_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "user_name", referencedColumnName = "user_name"
            )
    )
	List<UserProfile> requestingUsers;
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

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

	public List<BookAuthor> getBookAuthors() {
		return bookAuthors;
	}

	public void setBookAuthors(List<BookAuthor> bookAuthors) {
		this.bookAuthors = bookAuthors;
	}

	public BookCategory getBookCategory() {
		return BookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		BookCategory = bookCategory;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public List<UserProfile> getRequestingUsers() {
		return requestingUsers;
	}

	public void setRequestingUsers(List<UserProfile> requestingUsers) {
		this.requestingUsers = requestingUsers;
	}
}
