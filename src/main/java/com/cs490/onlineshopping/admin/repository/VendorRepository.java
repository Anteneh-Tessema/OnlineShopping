package com.cs490.onlineshopping.admin.repository;

import com.cs490.onlineshopping.admin.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor,Integer> {
}
