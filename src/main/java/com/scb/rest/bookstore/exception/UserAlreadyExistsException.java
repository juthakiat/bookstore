/**
 *
 */
package com.scb.rest.bookstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Juthakiat Tipchai
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException(String message) {
		super("The user \'" + message + "\' is already exists");
	}

}
