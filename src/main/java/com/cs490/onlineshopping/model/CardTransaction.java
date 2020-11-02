package com.cs490.onlineshopping.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class CardTransaction {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="card_id")
    private Card card;
    @Column(name = "amount")
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private PaymentType transactionType;
    private PaymentStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card cardId) {
        this.card = cardId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(PaymentType transactionType) {
        this.transactionType = transactionType;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
