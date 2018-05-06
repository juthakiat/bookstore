/**
 * 
 */
package com.scb.rest.bookstore.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.scb.rest.bookstore.entity.serializer.CustomDateDeserializer;

/**
 * @author Juthakiat Tipchai
 *
 */
@Entity
public class User {

	@Id
	@GeneratedValue
	@JsonIgnore
	private Integer id;

	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private String username;
	
	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private String name;

	private String surname;

	@Past
	@Column(name = "date_of_birth")
	@JsonProperty("date_of_birth")
	@JsonDeserialize(using = CustomDateDeserializer.class)
	private Date dateOfBirth;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<BookOrder> orders = new HashSet<>();

	public User() {

	}

	public User(String username, String password, String name, String surname, Date dateOfBirth) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Set<BookOrder> getOrders() {
		return orders;
	}

	public void setOrders(Set<BookOrder> orders) {
		this.orders = orders;
	}

	public List<Integer> getBooks() {
		List<Integer> books = new ArrayList<>();
		for (BookOrder order : orders) {
			books.add(order.getBook().getId());
		}
		return books;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", dateOfBirth=" + dateOfBirth + "]";
	}
	
}
