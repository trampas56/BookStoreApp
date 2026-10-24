package com.example.BookStoreApp.strategies;

import java.util.List;

import com.example.BookStoreApp.formsdata.SearchFormData;
import com.example.BookStoreApp.model.Book;
import com.example.BookStoreApp.model.BookAuthor;

public class ExactSearchStrategy extends TemplateSearchStrategy {

	@Override
	protected List<Book> makeInitialListOfBooks(SearchFormData searchDto) {
		return bookDao.findByTitle(searchDto.getTitle());
	}

	@Override
	protected boolean checkIfAuthorsMatch(SearchFormData searchFormData, Book book) {
		for (int authorId : searchFormData.getBookAuthorsId()) {
            boolean found = false;
            
            for (BookAuthor bookAuthor : book.getBookAuthors()) {

                if (authorId == bookAuthor.getAuthorId()) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            }
        }
		
		if(book.getBookAuthors().size() == searchFormData.getBookAuthorsId().size()) {
			return true;
		} else {
			return false;
		}
	}	
}
