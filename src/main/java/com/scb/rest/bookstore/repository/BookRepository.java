/**
 * 
 */
package com.scb.rest.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.scb.rest.bookstore.entity.Book;

/**
 * @author Juthakiat Tipchai
 *
 */
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query("SELECT b FROM Book b ORDER BY is_recommended DESC, book_name ASC") 
	List<Book> findAll();
	
}