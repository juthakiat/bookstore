package com.scb.rest.bookstore.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.scb.rest.bookstore.entity.Book;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
    private BookRepository bookRepository;
	
	@Test
	public void whenFindAll_thenReturnSortedListOfBooks() {
	    // given
		Book book = new Book("When Never Comes", "Barbara Davis", 179, false);
		entityManager.persist(book);
	    
		book = new Book("Before We Were Yours: A Novel", "Lisa Wingate", 340, false);
	    entityManager.persist(book);
	    
	    book = new Book("The Great Alone: A Novel Kristin Hannah", "Kristin Hannah", 495, true);
	    entityManager.persist(book);
	    
	    entityManager.flush();
	 
	    // when
	    List<Book> books = bookRepository.findAll();
	    
	    // then
	    assertEquals(3, books.size());
	    assertTrue(books.get(0).isRecommended());
	}
	
}
