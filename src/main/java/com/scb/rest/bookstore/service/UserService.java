/**
 * 
 */
package com.scb.rest.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.scb.rest.bookstore.entity.User;
import com.scb.rest.bookstore.exception.UserAlreadyExistsException;
import com.scb.rest.bookstore.repository.UserRepository;

/**
 * @author Juthakiat Tipchai
 *
 */
@Component
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

	public User create(User user) {
		if (this.findByUsername(user.getUsername()) != null) {
			throw new UserAlreadyExistsException(user.getUsername());
		}
		user.setPassword(getPasswordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findByUsername(String username) {
		Optional<User> user = userRepository.findByUsername(username);
		return (user.isPresent()) ? user.get() : null;
	}
	
	public void delete(User user) {
		userRepository.delete(user);
	}
}
