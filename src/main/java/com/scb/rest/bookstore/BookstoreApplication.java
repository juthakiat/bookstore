package com.scb.rest.bookstore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.scb.rest.bookstore.entity.Book;
import com.scb.rest.bookstore.service.BookService;

@SpringBootApplication
public class BookstoreApplication {

	private static final String PUBLISHER_URI = "https://scb-test-book-publisher.herokuapp.com/books";
	private static final String RECOMENDED_PUBLISHER_URI = "https://scb-test-book-publisher.herokuapp.com/books/recommendation";

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	@Autowired
	CommandLineRunner init(BookService bookService) {
		return (evt) -> {
			// Fetch books from external publisher’s services
			List<Book> books = bookService.fetchBooks(PUBLISHER_URI);
			if (!books.isEmpty()) {
				bookService.createBooks(books, false);
			}
			books = bookService.fetchBooks(RECOMENDED_PUBLISHER_URI);
			if (!books.isEmpty()) {
				bookService.createBooks(books, true);
			}
		};
	}
}
