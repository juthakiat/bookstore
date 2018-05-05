/**
 * 
 */
package com.scb.rest.bookstore.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Juthakiat Tipchai
 *
 */
@Entity
public class Book {

	@Id
	private Integer id;

	@JsonProperty("book_name")
	@Column(name = "book_name")
	private String bookName;

	@JsonProperty("author_name")
	@Column(name = "author_name")
	private String authorName;

	private float price;

	@JsonProperty("is_recommended")
	@Column(name = "is_recommended")
	private boolean recommended;

	@JsonIgnore
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private Set<BookOrder> orders = new HashSet<>();

	public Book() {

	}

	public Book(String bookName, String authorName, float price, boolean recommended) {
		this.bookName = bookName;
		this.authorName = authorName;
		this.price = price;
		this.recommended = recommended;
	}
	
	public Integer getId() {
		return id;
	}

	public String getBookName() {
		return bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public float getPrice() {
		return price;
	}

	public boolean isRecommended() {
		return recommended;
	}

	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}

	public Set<BookOrder> getOrders() {
		return orders;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", authorName=" + authorName + ", price=" + price
				+ ", recommended=" + recommended + "]";
	}
	
}
