package com.scb.rest.bookstore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BookOrder {
	
	@Id
	@GeneratedValue
	@JsonIgnore
	private Integer id;
	
	@JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User user;
	
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    @ManyToOne
    private Book book;
    
    @Column(name = "order_date")
	private Date orderDate;

	public BookOrder() {

	}

	public BookOrder(User user, Book book, Date orderDate) {
		this.user = user;
		this.book = book;
		this.orderDate = orderDate;
	}

	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Book getBook() {
		return book;
	}

	public Date getOrderDate() {
		return orderDate;
	}
	
}
