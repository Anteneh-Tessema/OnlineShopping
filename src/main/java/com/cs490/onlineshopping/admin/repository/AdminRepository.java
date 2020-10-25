package com.cs490.onlineshopping.admin.repository;

import com.cs490.onlineshopping.admin.model.Admin;
import com.cs490.onlineshopping.admin.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends PagingAndSortingRepository<Admin,Integer> {
}