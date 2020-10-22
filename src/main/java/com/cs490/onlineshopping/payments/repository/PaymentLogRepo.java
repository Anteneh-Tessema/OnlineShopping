package com.cs490.onlineshopping.payments.repository;

import com.cs490.onlineshopping.payments.model.PaymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PaymentLogRepo extends JpaRepository<PaymentLog, Long> {
}
