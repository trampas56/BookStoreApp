package com.example.BookStoreApp.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="authors")
public class BookAuthor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="author_id")
	private int authorId;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(mappedBy = "bookAuthors")
	List<Book> book;
	
	public BookAuthor() {}
	
	public BookAuthor(String name) {
		this.name = name;
	}

	public BookAuthor(int authorId, String name, List<Book> book) {
		super();
		this.authorId = authorId;
		this.name = name;
		this.book = book;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBook() {
		return book;
	}

	public void setBook(List<Book> book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "BookAuthor [authorId=" + authorId + ", name=" + name + ", book=" + book + "]";
	}
}
