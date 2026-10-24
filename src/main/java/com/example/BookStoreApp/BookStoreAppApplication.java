package com.example.BookStoreApp;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.BookStoreApp.dao.BookCategoryDAO;
import com.example.BookStoreApp.model.BookCategory;

@SpringBootApplication
public class BookStoreAppApplication {


    @Autowired
    private BookCategoryDAO bookCategoryDAO;
    
	public static void main(String[] args) {
		SpringApplication.run(BookStoreAppApplication.class, args);
	}
	
	@Bean
    CommandLineRunner initDatabase() {
        return args -> {
            if (bookCategoryDAO.count() == 0) {  // Check if the table is empty
                List<BookCategory> categories = Arrays.asList(
                    new BookCategory("Art"),
                    new BookCategory("Comic"),
                    new BookCategory("Fantasy"),
                    new BookCategory("Fiction"),
                    new BookCategory("Biographies"),
                    new BookCategory("History"),
                    new BookCategory("Science"),
                    new BookCategory("Literature"),
                    new BookCategory("Adventure"),
                    new BookCategory("Crime"),
                    new BookCategory("Other")
                );
                bookCategoryDAO.saveAll(categories);
                System.out.println("Database initialized");
            }
        };
    }

}
