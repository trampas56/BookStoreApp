# Social bookstore app

A Spring Boot web app where users trade books with each other. You sign up, make a profile with your favourite authors and categories, offer your own books and request books from other users. The owner of a book sees who asked for it and accepts one of them, and everyone who asked gets a notification saying if they got it or not. You can also search for books, with exact or approximate matching.

It uses Spring Boot, Spring Security (passwords are stored with BCrypt), Thymeleaf and MySQL. There's a user and an admin role.

## Running it

You need Java 17, Maven and MySQL with a database called `bookstoreapp`. The app connects as `root` and reads the password from the `DB_PASSWORD` environment variable:

```bash
DB_PASSWORD=your_mysql_password mvn spring-boot:run
```

Then open http://localhost:8080. The tables are created automatically and the book categories are added on startup.

The sprint report is in [report.pdf](report.pdf).
