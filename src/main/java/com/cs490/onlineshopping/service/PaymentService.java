package com.cs490.onlineshopping.service;

import com.cs490.onlineshopping.dto.MakePaymentDTO;
import com.cs490.onlineshopping.dto.PaymentDTO;
import com.cs490.onlineshopping.model.*;

import com.cs490.onlineshopping.repository.PaymentRepository;

import com.cs490.onlineshopping.repository.UserRepository;

import com.cs490.onlineshopping.smtp.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepo;

    @Autowired
    CardService cardService;

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    OrderService orderService;

    @Autowired
    EmailSender emailSender;

    public void payForItems(MakePaymentDTO makePaymentDto)
    {
        //pay from customer account
        payFromCustomerAccount(makePaymentDto);
        //pay to vendor account
        //String vendorCardNumber = "temp";
        //payToVendorAccount(makePaymentDto);
        sendRecieptEmail(makePaymentDto);
    }

    private Long payFromCustomerAccount(MakePaymentDTO dto)
    {
        cardService.withdrawFromCard(dto.getCardNumber(),dto.getCardExpiryDate(),dto.getSecurityCode(),dto.getPaymentMethod(),dto.getAmount());
        Payment payment = new Payment();
        payment.setOrder(orderService.findById(dto.getOrderId()).get());
        payment.setAmount(dto.getAmount());
        payment.setPaymentType(PaymentType.PAYMENT_FROM);
        payment.setCardNumber(dto.getCardNumber());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setStatusDescription("Transaction finished successfully");
        payment.setTransactionTime(new Date());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setUser(userRepository.getOne(dto.getCustomerUserId()));
        return paymentRepo.save(payment).getId();
    }

    private void sendRecieptEmail(MakePaymentDTO dto)
    {
        Order order = orderService.findById(dto.getOrderId()).get();
        StringBuilder emailMessage = new StringBuilder("Hello Customer \n");
        emailMessage.append("We have received payment of "+dto.getAmount()+" for the following items");
        for(OrderItem item : order.getOrderItems())
        {
            emailMessage.append(item.getProduct().getName()+ " "+item.getQuantity()+" unit(s)");
        }
        String email = userRepository.getOne(dto.getCustomerUserId()).getEmail();
        String subject = "Reciept for payment of goods";
        emailSender.sendSimpleMessage(email,subject,emailMessage.toString());
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
    
    public Payment getPayment(Long order_id) {
    	Optional<Order> order = orderService.findById(order_id);
    	if(order.isPresent()) {
    		return paymentRepo.findByOrder(order.get());
    	}
    	throw new IllegalArgumentException("Order cannot be found");
    	
    }

    private PaymentDTO getDTO(Payment payment)
    {
        PaymentDTO dto = new PaymentDTO();
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus());
        dto.setStatusDescription(payment.getStatusDescription());
        dto.setMethod(payment.getMethod());
        dto.setUserId(payment.getUser().getId());
        return dto;
    }

}
