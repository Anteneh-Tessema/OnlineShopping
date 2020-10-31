package com.cs490.onlineshopping.dto;

import com.cs490.onlineshopping.model.PaymentStatus;
import com.cs490.onlineshopping.model.PaymentType;

import java.math.BigDecimal;

public class PaymentDTO {
    private Integer userId;
    private BigDecimal amount;
    private PaymentStatus status;
    private String statusDescription;
    private PaymentType type;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

