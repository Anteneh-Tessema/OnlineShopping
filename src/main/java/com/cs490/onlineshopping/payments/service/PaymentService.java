package com.cs490.onlineshopping.payments.service;

import com.cs490.onlineshopping.payments.repository.PaymentLogRepo;
import com.cs490.onlineshopping.payments.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    PaymentLogRepo paymentLogRepo;
}
