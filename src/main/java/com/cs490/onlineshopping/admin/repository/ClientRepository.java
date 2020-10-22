package com.cs490.onlineshopping.admin.repository;

import com.cs490.onlineshopping.admin.model.Client;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends PagingAndSortingRepository<Client,Integer> {
}
