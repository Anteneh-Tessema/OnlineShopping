package com.cs490.onlineshopping.admin.repository;

import com.cs490.onlineshopping.admin.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository  extends JpaRepository<Client,Integer> {
}
