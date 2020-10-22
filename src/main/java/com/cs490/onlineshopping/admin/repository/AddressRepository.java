package com.cs490.onlineshopping.admin.repository;

import com.cs490.onlineshopping.admin.model.Address;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends PagingAndSortingRepository<Address,Integer> {
}
