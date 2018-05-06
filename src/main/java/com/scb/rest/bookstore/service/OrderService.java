/**
 * 
 */
package com.scb.rest.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scb.rest.bookstore.entity.BookOrder;
import com.scb.rest.bookstore.repository.OrderRepository;

/**
 * @author Juthakiat Tipchai
 *
 */
@Component
public class OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public BookOrder create(BookOrder order) {
		return orderRepository.save(order);
	}
	
	public void deleteByUserId(Integer id) {
		orderRepository.deleteByUserId(id);
	}
}
