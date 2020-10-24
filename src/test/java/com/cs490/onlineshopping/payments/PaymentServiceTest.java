package com.cs490.onlineshopping.payments;

import com.cs490.onlineshopping.payments.dto.MakePaymentDTO;
import com.cs490.onlineshopping.payments.dto.PaymentDTO;
import com.cs490.onlineshopping.payments.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class PaymentServiceTest {
    @Autowired
    PaymentService paymentService;

    @Test
    public void testPayForItems()
    {
        MakePaymentDTO paymentDTO = new MakePaymentDTO();
        paymentDTO.setAmount(new BigDecimal(1000));
        paymentDTO.setCardNumber("5551114444");
        paymentDTO.setCustomerUserId("cust1");
        paymentDTO.setVenderUserId("vendor1");
        paymentService.payForItems(paymentDTO);

        List<PaymentDTO> custPayments = paymentService.getUserPayments("cust1");
        assertTrue("Customer payments should not be empty",!custPayments.isEmpty());
        List<PaymentDTO> vendorPayments = paymentService.getUserPayments("vendor1");
        assertTrue("Vendor payments should not be empty",!vendorPayments.isEmpty());
    }
}
