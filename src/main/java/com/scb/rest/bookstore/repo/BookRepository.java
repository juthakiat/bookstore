/**
 * 
 */
package com.scb.rest.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scb.rest.bookstore.entity.Book;

/**
 * @author Juthakiat Tipchai
 *
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

}