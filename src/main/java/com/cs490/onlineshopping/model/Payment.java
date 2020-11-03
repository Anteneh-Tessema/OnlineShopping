package com.cs490.onlineshopping.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column(name = "card_number")
    private String cardNumber;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Column(name = "status_description")
    private String statusDescription;
    @Column(name = "transaction_time")
    @Temporal(TemporalType.TIME)
    private Date transactionTime;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @OneToOne
    private Order order;
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    public Payment() {
		
	}

	private Payment(User user, String cardNumber, PaymentStatus status, String statusDescription, Date transactionTime,
			BigDecimal amount, PaymentType paymentType, Order order, PaymentMethod method) {
		super();
		this.user = user;
		this.cardNumber = cardNumber;
		this.status = status;
		this.statusDescription = statusDescription;
		this.transactionTime = transactionTime;
		this.amount = amount;
		this.paymentType = paymentType;
		this.order = order;
		this.method = method;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
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

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

	public PaymentMethod getMethod() {
		
		return method;
	}
	
	public void setPaymentMethod(PaymentMethod method) {
		this.method = method;
	}
}
