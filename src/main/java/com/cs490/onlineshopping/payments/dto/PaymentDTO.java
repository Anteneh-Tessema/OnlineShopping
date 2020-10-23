package com.cs490.onlineshopping.payments.dto;

import com.cs490.onlineshopping.payments.model.PaymentStatus;
import com.cs490.onlineshopping.payments.model.PaymentType;

import java.math.BigDecimal;

public class PaymentDTO {
    private String userId;
    private BigDecimal amount;
    private PaymentStatus status;
    private String statusDescription;
    private PaymentType type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }
}
