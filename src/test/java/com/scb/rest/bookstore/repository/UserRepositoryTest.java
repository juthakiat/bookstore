package com.scb.rest.bookstore.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.scb.rest.bookstore.entity.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
    private UserRepository userRepository;
	
	@Test
	public void whenFindByUsername_thenReturnUser() {
	    // given
	    User johnDoe= new User("john.doe", "thisismysecret", "John", "Doe",
					new Date());
	    entityManager.persist(johnDoe);
	    entityManager.flush();
	 
	    // when
	    Optional<User> found = userRepository.findByUsername(johnDoe.getUsername());
	 
	    // then
	    assertTrue(found.isPresent());
	}
	
	@Test
	public void whenFindByUsername_thenReturnNull() {
	    // given
	    User johnDoe= new User("john.doe", null, "John", "Doe",
					new Date());
	    entityManager.persist(johnDoe);
	    entityManager.flush();
	 
	    // when
	    Optional<User> found = userRepository.findByUsername(johnDoe.getUsername());
	 
	    // then
	    assertFalse(found.isPresent());
	}
}
