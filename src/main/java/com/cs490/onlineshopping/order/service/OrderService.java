package com.cs490.onlineshopping.order.service;

import com.cs490.onlineshopping.admin.model.User;
import com.cs490.onlineshopping.order.model.Order;
import com.cs490.onlineshopping.order.repository.OrderRepository;
import com.cs490.onlineshopping.orders.dto.OrderDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findByUser(User user){
        return orderRepository.findByUser(user);
    }
    
    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }

	public Order saveOrder(Order order) throws Exception {
        return orderRepository.save(order);
    }
    
}
