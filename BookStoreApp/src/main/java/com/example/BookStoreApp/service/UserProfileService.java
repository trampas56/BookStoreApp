package com.example.BookStoreApp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.BookStoreApp.formsdata.BookAuthorFormData;
import com.example.BookStoreApp.formsdata.BookFormData;
import com.example.BookStoreApp.formsdata.SearchFormData;
import com.example.BookStoreApp.formsdata.UserProfileFormData;
import com.example.BookStoreApp.model.BookAuthor;

@Service
public interface UserProfileService {
	public void save(UserProfileFormData userProfile);
	public void addFavouriteBookAuthor(String username, BookAuthor author);
	List<BookFormData> retrieveBookOffers(String username);
	public void saveBookOffer(BookFormData bookFormData, String username);
	public List<BookAuthorFormData> getAllBookAuthors();
	public List<BookFormData> searchBooks(SearchFormData searchFormData, String username);
	public void requestBook(int bookId, String username);
	public void deleteBook(String username, int bookId);
	public List<UserProfileFormData> retrieveRequestingUsers(int bookId);
	public void acceptRequest(String username, String currentUsername, int bookId);
	public List<String> retrieveNotifications(String username);
}
