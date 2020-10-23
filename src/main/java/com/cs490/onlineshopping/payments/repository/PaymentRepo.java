package com.cs490.onlineshopping.payments.repository;

import com.cs490.onlineshopping.payments.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PaymentRepo extends JpaRepository<Payment, Long> {

    @Query(value = "select p from Payment p where p.userId = :userId")
    List<Payment> findByUserId(String userId);
}
