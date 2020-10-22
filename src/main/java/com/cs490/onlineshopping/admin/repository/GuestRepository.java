package com.cs490.onlineshopping.admin.repository;

import com.cs490.onlineshopping.admin.model.Guest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends PagingAndSortingRepository<Guest,Integer> {
}