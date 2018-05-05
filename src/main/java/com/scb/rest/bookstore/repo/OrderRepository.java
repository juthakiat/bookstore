package com.scb.rest.bookstore.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scb.rest.bookstore.entity.BookOrder;

public interface OrderRepository extends JpaRepository<BookOrder, Integer> {
	
}