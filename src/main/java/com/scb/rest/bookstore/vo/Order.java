/**
 * 
 */
package com.scb.rest.bookstore.vo;

import java.util.List;

/**
 * @author Juthakiat Tipchai
 *
 */
public class Order {
	
	private List<Integer> orders;
	
	public Order() {

	}

	public Order(List<Integer> orders) {
		this.orders = orders;
	}

	public List<Integer> getOrders() {
		return orders;
	}

}
