/**
 * 
 */
package com.scb.rest.bookstore.controller;

import java.security.Principal;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scb.rest.bookstore.entity.Book;
import com.scb.rest.bookstore.entity.BookOrder;
import com.scb.rest.bookstore.entity.User;
import com.scb.rest.bookstore.service.BookService;
import com.scb.rest.bookstore.service.OrderService;
import com.scb.rest.bookstore.service.UserService;
import com.scb.rest.bookstore.vo.Order;
import com.scb.rest.bookstore.vo.Quote;

/**
 * @author Juthakiat Tipchai
 *
 */
@RestController
public class OrderResource {

private UserService userService;
	
	private static final Logger LOG = LogManager.getLogger(UserService.class);

	private BookService bookService;
	
	private OrderService orderService;
	
	@Autowired
	public OrderResource(UserService userService, OrderService orderService) {
		this.userService = userService;
		this.orderService = orderService;
	}

	/**
	 * Order books and store order information in Orders table (DB). This returns the price for a 
successful order.
	 * @return
	 */
	@Transactional
	@PostMapping(path = "/users/orders")
	public Quote order(Principal principal, @RequestBody Order order) {
		LOG.info("\nCurrent logged in user: " + principal.getName() + "\n");
		User user = userService.findByUsername(principal.getName());
		Date orderDate = new Date();
		float price = 0f;
		for (Integer i: order.getOrders()) {
			Book book = bookService.findById(i);
			if (book != null) {
				BookOrder o = orderService.create(new BookOrder(user, book, orderDate));
				if (o != null) {
					price += book.getPrice();
				}
			}
		}
		return new Quote(price);
	}
}
