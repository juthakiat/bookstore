/**
 * 
 */
package com.scb.rest.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scb.rest.bookstore.entity.BookOrder;
import com.scb.rest.bookstore.repo.OrderRepository;

/**
 * @author Juthakiat Tipchai
 *
 */
@Component
public class OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	public BookOrder create(BookOrder order) {
		return orderRepository.save(order);
	}
}
