/**
 *
 */
package com.scb.rest.bookstore.controller;


import java.security.Principal;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scb.rest.bookstore.entity.User;
import com.scb.rest.bookstore.exception.UserNotFoundException;
import com.scb.rest.bookstore.service.OrderService;
import com.scb.rest.bookstore.service.UserService;

/**
 * @author Juthakiat Tipchai
 *
 * REST User Controller
 */
@RestController
public class UserResource {

	private UserService userService;

	private OrderService orderService;

	@Autowired
	public UserResource(UserService userService, OrderService orderService) {
		this.userService = userService;
		this.orderService = orderService;
	}

	/**
	 * Create a user account and store user’s information in Users table
	 * @param user User
	 */
	@PostMapping(path = "/users")
	public void createUser(@Valid @RequestBody User user) {
		userService.create(user);
	}

	/**
	 * Gets information about the logged in user. A successfully request returns information about the user and related books ordered.
	 * @param principal
	 */
	@GetMapping(path = "/users")
	public User getUserProfile(Principal principal) {
		User user = userService.findByUsername(principal.getName());
		if (user == null) {
			throw new UserNotFoundException("");
		}
		return user;
	}

	/**
	 * Delete logged in user’s record and order history.
	 * @param principal
	 */
	@Transactional
	@DeleteMapping(path = "/users")
	public void deleteUser(Principal principal) {
		String username = principal.getName();
		User user = userService.findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException(username);
		}
		orderService.deleteByUserId(user.getId());
		userService.delete(user);
	}
}
