package com.cs490.onlineshopping.order.repository;

import com.cs490.onlineshopping.order.model.OrderItem;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem,Integer> {

}