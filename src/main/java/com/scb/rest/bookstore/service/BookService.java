/**
 * 
 */
package com.scb.rest.bookstore.service;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.scb.rest.bookstore.entity.Book;
import com.scb.rest.bookstore.repository.BookRepository;

/**
 * @author Juthakiat Tipchai
 *
 */
@Component
public class BookService {

	private BookRepository bookRepository;

	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	/**
	 * Fetch books from external publisher’s services
	 * @param resourceUrl
	 *            String
	 * @return
	 */
	public List<Book> fetchBooks(String resourceUrl) throws UnknownHostException {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Book>> response = restTemplate.exchange(resourceUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Book>>() {
				});
		return response.getBody();
	}

	/**
	 * Persist books to database
	 * @param books
	 *            List<Book>
	 * @param isRecommended
	 *            boolean
	 */
	public void createBooks(List<Book> books, boolean isRecommended) {
		for (Book b : books) {
			b.setRecommended(isRecommended);
			bookRepository.save(b);
		}
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	/**
	 * Find a book by specify id
	 * @param id
	 *            Integer
	 * @return Book | null
	 */
	public Book findById(Integer id) {
		Optional<Book> book = bookRepository.findById(id);
		return (book.isPresent()) ? book.get() : null;
	}
}
