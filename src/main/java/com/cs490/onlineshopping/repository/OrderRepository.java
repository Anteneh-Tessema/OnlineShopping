package com.cs490.onlineshopping.repository;

import com.cs490.onlineshopping.model.User;
import com.cs490.onlineshopping.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

	List<Order> findByUser(User user);
}
