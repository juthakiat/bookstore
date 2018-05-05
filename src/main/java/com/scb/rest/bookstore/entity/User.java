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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	@JsonIgnore
	private String username;

	@JsonIgnore
	private String password;

	private String name;

	private String surname;

	@Past
	@JsonProperty("date_of_birth")
	@Column(name = "date_of_birth")
	private Date dateOfBirth;

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
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

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public Set<BookOrder> getOrders() {
		return orders;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", dateOfBirth=" + dateOfBirth + "]";
	}
	
}
