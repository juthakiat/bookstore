/**
 * 
 */
package com.scb.rest.bookstore.vo;

import java.util.List;

import com.scb.rest.bookstore.entity.Book;

/**
 * @author Juthakiat Tipchai
 *
 */
public class Books {
	
	private List<Book> books;

	public Books() {

	}

	public Books(List<Book> books) {
		this.books = books;
	}

	public List<Book> getBooks() {
		return books;
	}
	
}
