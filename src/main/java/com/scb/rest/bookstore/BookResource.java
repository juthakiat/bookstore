/**
 * 
 */
package com.scb.rest.bookstore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scb.rest.bookstore.entity.Book;
import com.scb.rest.bookstore.service.BookService;

/**
 * @author Juthakiat Tipchai
 *
 */
@RestController
public class BookResource {

	private BookService bookService;

	@Autowired
	public BookResource(BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * Returns the list sorted alphabetically with the recommended books always appears first. There should be no duplicated books in the list.
	 */
	@GetMapping(path = "/books")
	public List<Book> getBooks() {
		return bookService.findAll();
	}
}
