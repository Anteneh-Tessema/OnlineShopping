package com.cs490.onlineshopping.service;

import com.cs490.onlineshopping.model.User;
import com.cs490.onlineshopping.model.Order;
import com.cs490.onlineshopping.repository.OrderRepository;

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
  
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

	public Order saveOrder(Order order) throws Exception {
        return orderRepository.save(order);
    }

	public List<Order> findByVendorId(Long vendorId) {
		return orderRepository.getOrderByVendorId(vendorId);
	}
    
}
