package com.cs490.onlineshopping.repository;


import com.cs490.onlineshopping.model.Payment;
import com.cs490.onlineshopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PaymentRepo extends JpaRepository<Payment, Long> {

    @Query(value = "select p from Payment p where p.user = :userId")
    List<Payment> findByUserId(User user);
}
