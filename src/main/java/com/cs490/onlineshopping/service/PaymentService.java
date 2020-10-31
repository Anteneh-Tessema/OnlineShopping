package com.cs490.onlineshopping.service;

import com.cs490.onlineshopping.dto.MakePaymentDTO;
import com.cs490.onlineshopping.dto.PaymentDTO;
import com.cs490.onlineshopping.model.Payment;
import com.cs490.onlineshopping.model.PaymentMethod;
import com.cs490.onlineshopping.model.PaymentStatus;
import com.cs490.onlineshopping.model.PaymentType;
import com.cs490.onlineshopping.repository.PaymentRepo;
import com.cs490.onlineshopping.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    PaymentRepo paymentRepo;

    @Autowired
    CardService cardService;

    @Autowired
    UserRepository userRepository;

    public void payForItems(MakePaymentDTO makePaymentDto)
    {
        //pay from customer account
        payFromCustomerAccount(makePaymentDto);
        //pay to vendor account
        //String vendorCardNumber = "temp";
        //payToVendorAccount(makePaymentDto);
    }

    private Long payFromCustomerAccount(MakePaymentDTO dto)
    {
        cardService.withdrawFromCard(dto.getCardNumber(),dto.getCardExpiryDate(),dto.getSecurityCode(),dto.getPaymentMethod(),dto.getAmount());
        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setPaymentType(PaymentType.PAYMENT_FROM);
        payment.setCardNumber(dto.getCardNumber());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setStatusDescription("Transaction finished successfully");
        payment.setTransactionTime(new Date());
        payment.setUser(userRepository.getOne(dto.getCustomerUserId()));
        return paymentRepo.save(payment).getId();
    }

    private Long payToVendorAccount(MakePaymentDTO dto)
    {
        String vendorCardNumber = "";
        String vendorCardExpDate = "";
        String vendorCardSecCode = "";
        PaymentMethod vendorCardType = PaymentMethod.VISA;
        cardService.depositToCard(vendorCardNumber,vendorCardExpDate,vendorCardSecCode,vendorCardType,dto.getAmount());
        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setPaymentType(PaymentType.PAYMENT_TO);
        payment.setCardNumber(dto.getCardNumber());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setStatusDescription("Transaction finished successfully");
        payment.setTransactionTime(new Date());
        payment.setUser(userRepository.getOne(dto.getVenderUserId()));
        return paymentRepo.save(payment).getId();
    }

    public List<PaymentDTO> getUserPayments(Long userId)
    {
        return paymentRepo.findByUserId(userRepository.getOne(userId)).stream().map(p -> getDTO(p)).collect(Collectors.toList());
    }

    private PaymentDTO getDTO(Payment payment)
    {
        PaymentDTO dto = new PaymentDTO();
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus());
        dto.setStatusDescription(payment.getStatusDescription());
        dto.setType(payment.getPaymentType());
        dto.setUserId(payment.getUser().getId());
        return dto;
    }

}
