package com.cs490.onlineshopping.repository;

import com.cs490.onlineshopping.model.User;
import com.cs490.onlineshopping.model.Order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order,Long> {

	List<Order> findByUser(User user);
    
	@Query("SELECT o FROM Order o JOIN o.orderItems i WHERE i.product.vendor.id = ?1")
	List<Order> getOrderByVendorId(Long vendorId);
};