package com.cs490.onlineshopping.repository;

import com.cs490.onlineshopping.model.Order;
import com.cs490.onlineshopping.model.OrderItem;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem,Long> {

	List<OrderItem> findByOrder(Order order);

}