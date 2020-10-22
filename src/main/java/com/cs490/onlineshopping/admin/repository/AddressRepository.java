package com.cs490.onlineshopping.admin.repository;

import com.cs490.onlineshopping.admin.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
