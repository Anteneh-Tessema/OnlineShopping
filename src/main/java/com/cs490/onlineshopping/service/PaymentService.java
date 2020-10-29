package com.cs490.onlineshopping.service;

import com.cs490.onlineshopping.dto.MakePaymentDTO;
import com.cs490.onlineshopping.dto.PaymentDTO;
import com.cs490.onlineshopping.model.Payment;
import com.cs490.onlineshopping.model.PaymentStatus;
import com.cs490.onlineshopping.model.PaymentType;
import com.cs490.onlineshopping.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepo;

    public void payForItems(MakePaymentDTO makePaymentDto)
    {
        //pay from customer account
        pay(makePaymentDto.getAmount(), PaymentType.PAYMENT_FROM, makePaymentDto.getCardNumber(), makePaymentDto.getCustomerUserId());
        //pay to vendor account
        String vendorCardNumber = "temp";
        pay(makePaymentDto.getAmount(),PaymentType.PAYMENT_TO,vendorCardNumber, makePaymentDto.getVenderUserId());
    }

    private Long pay(BigDecimal amount, PaymentType paymentType, String cardNumber, String userId)
    {
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentType(paymentType);
        payment.setCardNumber(cardNumber);
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setStatusDescription("Transaction finished successfully");
        payment.setTransactionTime(new Date());
        payment.setUserId(userId);
        return paymentRepo.save(payment).getId();
    }

    public List<PaymentDTO> getUserPayments(String userId)
    {
        return paymentRepo.findByUserId(userId).stream().map(p -> getDTO(p)).collect(Collectors.toList());
    }

    private PaymentDTO getDTO(Payment payment)
    {
        PaymentDTO dto = new PaymentDTO();
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus());
        dto.setStatusDescription(payment.getStatusDescription());
        dto.setType(payment.getPaymentType());
        dto.setUserId(payment.getUserId());
        return dto;
    }

}
