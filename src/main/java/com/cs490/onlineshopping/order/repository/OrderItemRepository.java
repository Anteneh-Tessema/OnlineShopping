package com.cs490.onlineshopping.order.repository;

import com.cs490.onlineshopping.admin.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface OrderItemRepository extends PagingAndSortingRepository<Product,Integer> {

}