package com.scb.rest.bookstore.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scb.rest.bookstore.entity.BookOrder;

public interface OrderRepository extends JpaRepository<BookOrder, Integer> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM BookOrder o WHERE o.user.id = :userId")
	void deleteByUserId(@Param("userId") Integer userId);

}