package com.cs490.onlineshopping.payments.dto;

import java.math.BigDecimal;

public class MakePaymentDTO {
    private String customerUserId;
    private String venderUserId;
    private BigDecimal amount;
    private String cardNumber;

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
}
