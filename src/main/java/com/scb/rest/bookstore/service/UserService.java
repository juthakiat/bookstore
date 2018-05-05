/**
 * 
 */
package com.scb.rest.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scb.rest.bookstore.entity.User;
import com.scb.rest.bookstore.repo.UserRepository;

/**
 * @author Juthakiat Tipchai
 *
 */
@Component
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public User create(User user) {
		return userRepository.save(user);
	}
}
