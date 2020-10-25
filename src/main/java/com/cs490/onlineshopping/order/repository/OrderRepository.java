package com.cs490.onlineshopping.order.repository;

import com.cs490.onlineshopping.admin.model.User;
import com.cs490.onlineshopping.order.model.Order;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order,Integer> {

	List<Order> findByUser(User user);
    
}