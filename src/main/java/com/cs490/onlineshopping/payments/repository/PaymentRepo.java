package com.cs490.onlineshopping.payments.repository;

import com.cs490.onlineshopping.payments.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
