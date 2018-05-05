package com.scb.rest.bookstore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.scb.rest.bookstore.entity.Book;
import com.scb.rest.bookstore.entity.BookOrder;
import com.scb.rest.bookstore.entity.User;
import com.scb.rest.bookstore.service.BookService;
import com.scb.rest.bookstore.service.OrderService;
import com.scb.rest.bookstore.service.UserService;

@SpringBootApplication
public class BookstoreApplication {

	private static final String PUBLISHER_URI = "https://scb-test-book-publisher.herokuapp.com/books";
	private static final String RECOMENDED_PUBLISHER_URI = "https://scb-test-book-publisher.herokuapp.com/books/recommendation";

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	@Autowired
	CommandLineRunner init(BookService bookService, UserService userService, OrderService orderService) {
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

			// Create a new user 'John Doe'
			// TODO: Encrypt the password before saving
			User user = userService.create(new User("john.doe", "thisismysecret", "John", "Doe",
					new SimpleDateFormat("dd/MM/yyyy").parse("15/01/1985")));

			// Create an order if the book exists.
			Date orderDate = new Date();
			Book book = bookService.findById(1);
			if (book != null) {
				orderService.create(new BookOrder(user, book, orderDate));
			}
			book = bookService.findById(4);
			if (book != null) {
				orderService.create(new BookOrder(user, book, orderDate));
			}
		};
	}
}
