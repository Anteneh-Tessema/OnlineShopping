package com.cs490.onlineshopping.repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cs490.onlineshopping.model.Product;
import com.cs490.onlineshopping.model.Vendor;

import org.springframework.data.domain.Sort;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {

    List<Product> findByVendor(Vendor vendor);
}