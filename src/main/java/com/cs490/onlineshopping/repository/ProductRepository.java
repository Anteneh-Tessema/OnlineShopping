package com.cs490.onlineshopping.repository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.cs490.onlineshopping.model.Category;
import com.cs490.onlineshopping.model.Product;
import com.cs490.onlineshopping.model.Vendor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {

    Page<Product> findByVendor(Vendor vendor, Pageable pageable);    
    
    Page<Product> findByNameContaining(String name, Pageable pageable);
    
    List<Product> findByCategory(Category category);

    @Query("SELECT p FROM Product p where p.vendor.id = ?1")
    List<Product> findByVendorId(Long vendorId);
    
}