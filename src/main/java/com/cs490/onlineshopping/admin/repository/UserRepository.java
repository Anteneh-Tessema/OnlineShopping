package com.cs490.onlineshopping.admin.repository;

import com.cs490.onlineshopping.admin.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {
}