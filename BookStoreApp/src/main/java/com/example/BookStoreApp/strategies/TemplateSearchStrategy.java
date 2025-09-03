package com.example.BookStoreApp.strategies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.BookStoreApp.dao.BookDao;
import com.example.BookStoreApp.formsdata.BookFormData;
import com.example.BookStoreApp.formsdata.SearchFormData;
import com.example.BookStoreApp.model.Book;
import com.example.BookStoreApp.model.BookAuthor;
import com.example.BookStoreApp.model.UserProfile;

public abstract class TemplateSearchStrategy implements SearchStrategy{
	@Autowired
	protected BookDao bookDao;
	
	public ArrayList<BookFormData> search(SearchFormData bookFormData, BookDao bookDao, UserProfile currentUser) {
		this.bookDao = bookDao;
		
		List<Book> listBooks = makeInitialListOfBooks(bookFormData);
		ArrayList<BookFormData> listFormData = new ArrayList<>();
		
		for(Book book : listBooks) {
			
			boolean check = checkIfAuthorsMatch(bookFormData, book);
			if(check && !isCurrentUserBookOffer(book, currentUser)) {			
				BookFormData formData = new BookFormData();
				formData.setBookId(book.getBookId());
				formData.setTitle(book.getTitle());
				formData.setSummary(book.getSummary());
				formData.setCategoryName(book.getBookCategory().getName());
            
				StringBuilder stringBuilder = new StringBuilder();

				for (int i = 0; i < book.getBookAuthors().size(); i++) {
					BookAuthor author = book.getBookAuthors().get(i);
					stringBuilder.append(author.getName());

					if (i < book.getBookAuthors().size() - 1) {
                    stringBuilder.append(", ");
					}
				}

				formData.setBookAuthors(stringBuilder.toString());
				listFormData.add(formData);
			}
		}
		return listFormData;
	}
		
	private boolean isCurrentUserBookOffer(Book book, UserProfile currentUser) {
	    if (currentUser != null && book.getUserProfile().getUsername().equals(currentUser.getUsername())) {
	        return true;
	    }
	    
	    if (book.getRequestingUsers().contains(currentUser)) {
	    	return true;
	    }
	    
	    return false;
	}
	
	protected abstract List<Book> makeInitialListOfBooks(SearchFormData searchDto);
	protected abstract boolean checkIfAuthorsMatch(SearchFormData searchFormData, Book book);
}
