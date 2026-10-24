package com.example.BookStoreApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import com.example.BookStoreApp.formsdata.BookFormData;
import com.example.BookStoreApp.formsdata.SearchFormData;
import com.example.BookStoreApp.formsdata.UserProfileFormData;
import com.example.BookStoreApp.service.BookAuthorService;
import com.example.BookStoreApp.service.UserProfileService;
import com.example.BookStoreApp.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    
    @Autowired
    BookAuthorService bookAuthorService;
    
    @Autowired
    UserProfileService userProfileService;

    @RequestMapping("/user/dashboard")
    public String getUserHome(){
//    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		 String currentPrincipalName = authentication.getName();
//		 System.err.println(currentPrincipalName);
		
        return "user/dashboard";
    }
    
    @RequestMapping("/createProfile")
    public String createProfile(){
    	return "createProfile/createUserprofile";
    }
    
    @RequestMapping("/makeprofile")
    public String retrieveProfile(@RequestParam("username") String username, Model model) {
    	UserProfileFormData profile = new UserProfileFormData();
    	profile.setUsername(username);
    	model.addAttribute("userProfile", profile);
    	return "userProfile/userProfile";
    }

    
    @RequestMapping("/usermainmenu")
    public String getUserMainMenu(){
    	return "user/dashboard";   	
    }
    
    //@SuppressWarnings("deprecation")
	@RequestMapping("/saveprofile")
    public String saveProfile(@ModelAttribute("userProfile") UserProfileFormData userProfile, Model model) {
		//System.out.println("1");

		userProfileService.save(userProfile);
		 
        model.addAttribute("successMessage", "Profile made successfully!");
        return "redirect:/";
    }
	
	@RequestMapping("/booklist")
    public String bookList(HttpServletRequest request, Model model) {
        String username = (String) request.getSession().getAttribute("username");
        
        List<BookFormData> books = userProfileService.retrieveBookOffers(username);
        model.addAttribute("books", books);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        List<BookFormData> bookOffers = userProfileService.retrieveBookOffers(currentUsername);
        model.addAttribute("bookOffers", bookOffers);


     
        return "bookoffers/booklist";
    }
	
	@RequestMapping("/bookform")
    public String showAddBookForm(Model model) {
        model.addAttribute("bookFormData", new BookFormData());
        userProfileService.retrieveBookOffers(getUserHome());
        
        return "addbook/bookform";
    }
	
	@PostMapping("/addbook")
    public String addBook(@ModelAttribute BookFormData bookFormData, HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        
        userProfileService.saveBookOffer(bookFormData, currentUsername);

        return "redirect:/booklist";
    }
    
	@RequestMapping("/searchForm")
    public String showSearchform(Model model) {
        model.addAttribute("searchFormData", new SearchFormData());
        model.addAttribute("listBookAuthors", userProfileService.getAllBookAuthors());
        
        return "search/searchForm";
    }
	
	@PostMapping("/search")
    public String search(@ModelAttribute SearchFormData searchFormData, HttpServletRequest request, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUserName = authentication.getName();
	    
        model.addAttribute("listBooks", userProfileService.searchBooks(searchFormData, currentUserName));
        return "search/booklist"; // ftiaxnw selida me munhma epityxias 
    }
	
	@RequestMapping("/requestBook")
	public String requestBook(@RequestParam("bookId") int bookId, Model model) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();


	    userProfileService.requestBook(bookId, username);

	    return "search/booklist";
	}
	
	@RequestMapping("/deleteBookOffer")
	public String deleteBookOffer(@RequestParam("bookId") int bookId, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
		
	    userProfileService.deleteBook(username, bookId);
		 
		return "redirect:/booklist";
	}
	
	@RequestMapping("/showRequestingUsersForBookOffer")
	public String showRequestingUsersForBookOffer(@RequestParam("bookId") int bookId, Model model) {
		model.addAttribute("userProfiles", userProfileService.retrieveRequestingUsers(bookId));
		model.addAttribute("bookId", bookId);
		return "requests/requests";
	}
	
	@RequestMapping("/acceptRequest")
	public String acceptRequest(@RequestParam("username") String username, @RequestParam("bookId") int bookId, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    
		userProfileService.acceptRequest(username, currentUsername, bookId);
		
		return "redirect:/booklist";
	}
	
	@RequestMapping("/showNotifications")
	public String acceptRequest(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		model.addAttribute("notifications", userProfileService.retrieveNotifications(username));
		
		return "notify/notify";
	}
}

