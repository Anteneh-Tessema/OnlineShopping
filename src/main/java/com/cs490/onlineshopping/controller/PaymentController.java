package com.cs490.onlineshopping.controller;

import com.cs490.onlineshopping.dto.MakePaymentDTO;
import com.cs490.onlineshopping.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payments")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("")
    public ResponseEntity makePayment(MakePaymentDTO dto)
    {
        paymentService.payForItems(dto);
        return ResponseEntity.status(200).build();
    }
}
