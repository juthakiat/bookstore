/**
 * 
 */
package com.scb.rest.bookstore;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scb.rest.bookstore.entity.User;
import com.scb.rest.bookstore.service.UserService;

/**
 * @author Juthakiat Tipchai
 *
 */
@RestController
public class UserResource {
	
	private UserService userService;
	
	@Autowired
	public UserResource(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Create a user account and store user’s information in Users table
	 * @param user
	 */
	@PostMapping(path = "/users")
	public void createUser(@Valid @RequestBody User user) {
		userService.create(user);
	}
}
