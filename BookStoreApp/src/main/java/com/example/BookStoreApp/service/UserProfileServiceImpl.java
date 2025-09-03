package com.example.BookStoreApp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookStoreApp.dao.BookAuthorDAO;
import com.example.BookStoreApp.dao.BookCategoryDAO;
import com.example.BookStoreApp.dao.BookDao;
import com.example.BookStoreApp.dao.NotifyDAO;
import com.example.BookStoreApp.dao.UserProfileDAO;
import com.example.BookStoreApp.formsdata.BookAuthorFormData;
import com.example.BookStoreApp.formsdata.BookFormData;
import com.example.BookStoreApp.formsdata.SearchFormData;
import com.example.BookStoreApp.formsdata.UserProfileFormData;
import com.example.BookStoreApp.model.Book;
import com.example.BookStoreApp.model.BookAuthor;
import com.example.BookStoreApp.model.BookCategory;
import com.example.BookStoreApp.model.Notify;
import com.example.BookStoreApp.model.UserProfile;
import com.example.BookStoreApp.strategies.ApproximateSearchStrategy;
import com.example.BookStoreApp.strategies.ExactSearchStrategy;
import com.example.BookStoreApp.strategies.SearchStrategy;

import jakarta.transaction.Transactional;

@Service
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	private UserProfileDAO userProfileDAO;
	
	@Autowired
	BookAuthorService bookAuthorService;
	
	@Autowired
	BookCategoryDAO bookCategoryDAO;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private BookAuthorDAO bookAuthorDAO;
	
	@Autowired
	private NotifyDAO notifyDAO;

	@Override
	public void save(UserProfileFormData userProfileFormData) {		
		UserProfile userProfile = new UserProfile();
		userProfile.setUsername(userProfileFormData.getUsername());
		userProfile.setAddress(userProfileFormData.getAddress());
		userProfile.setPhoneNumber(userProfileFormData.getPhoneNumber());
		userProfile.setAge(userProfileFormData.getAge());
		userProfile.setFavouriteBookAuthors(new ArrayList<BookAuthor>());
		userProfile.setFavouriteBookCategories(new ArrayList<BookCategory>());
		 
		String[] authorNames =  userProfileFormData.getFavouriteBookAuthors().split(",");
		
		 for (String author : authorNames) {
		 
			BookAuthor existingAuthor = bookAuthorService.findByName(author);
			if (existingAuthor == null) { 
				BookAuthor newAuthor = new BookAuthor(author.trim());
				bookAuthorService.saveAuthor(newAuthor);
				userProfile.getFavouriteBookAuthors().add(newAuthor);
			}else {
				userProfile.getFavouriteBookAuthors().add(existingAuthor);
			}
		}
		 
		for(Integer id : userProfileFormData.getFavouriteBookCategories()) {
			BookCategory category = bookCategoryDAO.findById(id).get();
			userProfile.getFavouriteBookCategories().add(category);
		}
		 
        userProfileDAO.save(userProfile);	
	}
	
	@Override
	public void saveBookOffer(BookFormData bookFormData, String username) {
		Optional<UserProfile> userOptional = userProfileDAO.findByUsername(username);
	    UserProfile user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));
	    
	    Book bookOffer = new Book();
	    bookOffer.setTitle(bookFormData.getTitle());
	    bookOffer.setSummary(bookFormData.getSummary());
	    bookOffer.setUserProfile(user);  
	    user.getBookOffers().add(bookOffer);
	    
	    String[] authorNames =  bookFormData.getBookAuthors().split(",");
		
		 for (String author : authorNames) {
		 
			BookAuthor existingAuthor = bookAuthorService.findByName(author);
			if (existingAuthor == null) { 
				BookAuthor newAuthor = new BookAuthor(author.trim());
				bookAuthorService.saveAuthor(newAuthor);
				bookOffer.getBookAuthors().add(newAuthor);
			}else {
				bookOffer.getBookAuthors().add(existingAuthor);
			}
		}

		BookCategory category = bookCategoryDAO.findById(bookFormData.getCategoryId()).get();
		bookOffer.setBookCategory(category);
		  
	    bookDao.save(bookOffer);
    
	}
	
	/* @Override
	public List<BookFormData> retrieveBookOffers(String username) {
        List<Book> books = bookDao.findByUserProfileUsernameNot(username);
        List<BookFormData> bookForms = new ArrayList<>();
        for (Book book : books) {
            BookFormData formData = new BookFormData();
            formData.setTitle(book.getTitle());
            bookForms.add(formData);
        }
        return bookForms;
    } */
	
	@Override
	public List<BookFormData> retrieveBookOffers(String username) {
	    Optional<UserProfile> optionalUserProfile = userProfileDAO.findByUsername(username);
	    
	    if (optionalUserProfile.isPresent()) {
	        UserProfile userProfile = optionalUserProfile.get();
	        
	        List<Book> books = userProfile.getBookOffers();
	        
	        List<BookFormData> bookForms = new ArrayList<>();
	        for (Book book : books) {
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

	            bookForms.add(formData);
	        }
	        
	        return bookForms;
	    } else {
	        return Collections.emptyList();
	    }
	}



	
	@Transactional
    public void addFavouriteBookAuthor(String username, BookAuthor bookAuthor) {
        Optional<UserProfile> optionalUserProfile = userProfileDAO.findByUsername(username);
        if (optionalUserProfile.isPresent()) {
            UserProfile userProfile = optionalUserProfile.get();
            userProfile.getFavouriteBookAuthors().add(bookAuthor);
            userProfileDAO.save(userProfile);
        } else {
            throw new RuntimeException("User profile not found with username: " + username);
        }
    }
	
	public List<BookAuthorFormData> getAllBookAuthors() {
		List<BookAuthor> bookAuthors = bookAuthorDAO.findAll();
		List<BookAuthorFormData> bookAuthorsFormData = new ArrayList<>();
		
		for(int i = 0; i < bookAuthors.size(); i++) {
			BookAuthor bookAuthor = bookAuthors.get(i);
			BookAuthorFormData bookAuthorFormData = new BookAuthorFormData();
			bookAuthorFormData.setAuthorId(bookAuthor.getAuthorId());
			bookAuthorFormData.setName(bookAuthor.getName());
			bookAuthorsFormData.add(bookAuthorFormData);
		}
		return bookAuthorsFormData;
	}
	
	public List<BookFormData> searchBooks(SearchFormData searchFormData, String username) {
		SearchStrategy strategy;
		
		if(searchFormData.getStrategy() == 1) {
			strategy = new ExactSearchStrategy();
		} else if(searchFormData.getStrategy() == 2) {
			strategy = new ApproximateSearchStrategy();
		} else {
			strategy = null;
		}
		
		UserProfile currentUserProfile = userProfileDAO.findByUsername(username).get();
		return strategy.search(searchFormData, bookDao, currentUserProfile);
	}
	
	@Override
    public void requestBook(int bookId, String username) {
	    Optional<UserProfile> userProfileOptional = userProfileDAO.findByUsername(username);
	    UserProfile userProfile = userProfileOptional.orElseThrow(() -> new RuntimeException("User not found"));

	    Optional<Book> bookOptional = bookDao.findById(bookId);
	    Book book = bookOptional.orElseThrow(() -> new RuntimeException("Book not found"));

	    book.getRequestingUsers().add(userProfile);
	    userProfile.getRequestedBooks().add(book);

	    bookDao.save(book);
	    userProfileDAO.save(userProfile);
    }
	
	@Override
	public void deleteBook(String username, int bookId) {
	    Optional<Book> bookOptional = bookDao.findById(bookId);

        if(bookOptional.isPresent()) {
        	Book book = bookOptional.get();
        	if(book.getUserProfile().getUsername().equals(username)) {
        		bookDao.delete(book);
        	} else {
        		throw new RuntimeException("Cannot delete book offer of other user");
        	}		        
        } else {
        	throw new RuntimeException("Book not found with ID: " + bookId);
        }
	}

	@Override
	public List<UserProfileFormData> retrieveRequestingUsers(int bookId) {
		Optional<Book> book = bookDao.findById(bookId);
		
		if(!book.isPresent()) {
			return new ArrayList<UserProfileFormData>();
		}
		
		List<UserProfileFormData> userProfiles = new ArrayList<UserProfileFormData>();
        List<UserProfile> users = book.get().getRequestingUsers();
        
        for(int i = 0; i < users.size(); i++) {
        	UserProfileFormData userProfileFormData = new UserProfileFormData();
        	userProfileFormData.setUsername(users.get(i).getUsername());
        	userProfileFormData.setAddress(users.get(i).getAddress());
        	userProfileFormData.setPhoneNumber(users.get(i).getPhoneNumber());
        	userProfileFormData.setAge(users.get(i).getAge());
        	userProfiles.add(userProfileFormData);
        }
		return userProfiles;
	}

	@Override
	public void acceptRequest(String username, String currentUsername, int bookId) {
		Optional<Book> optionalBook = bookDao.findById(bookId);
		
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			for(UserProfile requestingUser : book.getRequestingUsers()) {
				if(requestingUser.getUsername().equals(username)) {
					Notify notify = new Notify(requestingUser, "Success for book with title = " + book.getTitle());
					notifyDAO.save(notify);
				} else {
					Notify notify = new Notify(requestingUser, "Failed for book with title = " + book.getTitle());
					notifyDAO.save(notify);
				}
			}			
			
			deleteBook(currentUsername, bookId);
		}		
	}

	@Override
	public List<String> retrieveNotifications(String username) {
		List<Notify> notifications = notifyDAO.findByUserProfileUsername(username);
		notifications.sort(Comparator.comparingInt(Notify::getNotifyId).reversed());
		
		return notifications.stream()
			    .map(Notify::getNotificationMessage)
			    .collect(Collectors.toList());
	}
}
