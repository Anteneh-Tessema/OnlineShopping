package com.cs490.onlineshopping.admin.repository;

import com.cs490.onlineshopping.admin.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Integer> {
}
