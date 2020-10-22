package com.cs490.onlineshopping.admin.repository;

import com.cs490.onlineshopping.admin.model.Vendor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends PagingAndSortingRepository<Vendor,Integer> {
}
