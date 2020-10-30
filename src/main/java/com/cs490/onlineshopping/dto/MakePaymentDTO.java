package com.cs490.onlineshopping.dto;

import com.cs490.onlineshopping.model.PaymentMethod;

import java.math.BigDecimal;

public class MakePaymentDTO {
    private String customerUserId;
    private String venderUserId;
    private BigDecimal amount;
    private String cardNumber;
    private String cardExpiryDate;
    private String securityCode;
    private PaymentMethod paymentMethod;

    public String getCustomerUserId() {
        return customerUserId;
    }

    public void setCustomerUserId(String customerUserId) {
        this.customerUserId = customerUserId;
    }

    public String getVenderUserId() {
        return venderUserId;
    }

    public void setVenderUserId(String venderUserId) {
        this.venderUserId = venderUserId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
