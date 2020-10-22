package com.cs490.onlineshopping.dao.payments;

import com.cs490.onlineshopping.entities.payments.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
