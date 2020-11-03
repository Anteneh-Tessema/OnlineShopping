package com.cs490.onlineshopping.dto;

import com.cs490.onlineshopping.model.PaymentMethod;
import com.cs490.onlineshopping.model.PaymentStatus;
import com.cs490.onlineshopping.model.PaymentType;

import java.math.BigDecimal;

public class PaymentDTO {
    private Long userId;
    private BigDecimal amount;
    private PaymentStatus status;
    private String statusDescription;
    private PaymentMethod method;
    private String cardNumber;

    public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }
}

