package com.cs490.onlineshopping.admin.repository;

import com.cs490.onlineshopping.admin.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
}
