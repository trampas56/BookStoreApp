package com.example.BookStoreApp.service;


import com.example.BookStoreApp.model.User;
import com.example.BookStoreApp.dao.UserDAO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserDAO userDAO;
	
	//@Autowired
	//private BookDao bookDao;
	
	/*public List<Book> test(String username){
		List<Book> books = bookDao.findAll();
		
		ArrayList<Book> showBooks = new ArrayList<>();
		for(Book book : books) {
			if(!book.getUserProfile().getUsername().equals(username)) {
				showBooks.add(book);
			}
		}
		
		//bookDao.findByUserProfileUsernameNot(username); 
		
		return showBooks;
	} */
	
	@Override
	public void saveUser(User user) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDAO.save(user);	
    }

	@Override
	public boolean isUserPresent(User user) {
		Optional<User> storedUser = userDAO.findByUsername(user.getUsername());
		return storedUser.isPresent();
	}

	// Method defined in Spring Security UserDetailsService interface
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// orElseThrow method of Optional container that throws an exception if Optional result  is null
		return userDAO.findByUsername(username).orElseThrow(
	                ()-> new UsernameNotFoundException(
	                        String.format("USER_NOT_FOUND %s", username)
	                ));
	}
}