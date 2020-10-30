package com.cs490.onlineshopping.service;

import com.cs490.onlineshopping.model.Order;
import com.cs490.onlineshopping.model.OrderItem;
import com.cs490.onlineshopping.repository.OrderItemRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

	public void saveItemOrder(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
	}

	public List<OrderItem> findByOrder(Order order) {
		return orderItemRepository.findByOrder(order);
	}

}
