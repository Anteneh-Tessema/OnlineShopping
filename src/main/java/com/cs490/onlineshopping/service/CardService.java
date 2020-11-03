package com.cs490.onlineshopping.service;

import com.cs490.onlineshopping.exception.CustomException;
import com.cs490.onlineshopping.model.*;
import com.cs490.onlineshopping.repository.CardRepository;
import com.cs490.onlineshopping.repository.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    CardTransactionRepository cardTransactionRepository;

    public void registerCard(String cardNumber, String firstName, String lastName, String expiryDate,
                             String secCode, PaymentMethod paymentMethod, BigDecimal accountBalance)
    {
        Card card = new Card();
        card.setAccountBalance(accountBalance);
        card.setCardNumber(cardNumber);
        card.setExpiryDate(expiryDate);
        card.setOwnerFirstName(firstName);
        card.setOwnerLastName(lastName);
        card.setSecurityCode(secCode);
        card.setPaymentMethod(paymentMethod);
        card = cardRepository.save(card);

        if(accountBalance.compareTo(BigDecimal.ONE) >= 0)
        {
            CardTransaction transaction = new CardTransaction();
            transaction.setAmount(accountBalance);
            transaction.setCard(card);
            transaction.setStatus(PaymentStatus.SUCCESS);
            transaction.setTransactionType(PaymentType.PAYMENT_TO);
            cardTransactionRepository.save(transaction);
        }
    }

    private void validateCardDetails(Card card,String expiryDate,String secCode,PaymentMethod paymentMethod
            ,BigDecimal amount)
    {
    	if(card == null || !card.getPaymentMethod().equals(paymentMethod) || !card.getSecurityCode().equals(secCode))
        {
            throw new CustomException("Invalid card details", HttpStatus.BAD_REQUEST);
        }
    }

    public void depositToCard(String cardNumber,String expiryDate,String secCode,PaymentMethod paymentMethod
                                ,BigDecimal amount)
    {
        Card card = cardRepository.findByCardNumber(cardNumber);
        validateCardDetails(card,expiryDate,secCode,paymentMethod,amount);
        card.setAccountBalance(card.getAccountBalance().add(amount));
        card = cardRepository.save(card);

        CardTransaction transaction = new CardTransaction();
        transaction.setTransactionType(PaymentType.PAYMENT_TO);
        transaction.setStatus(PaymentStatus.SUCCESS);
        transaction.setCard(card);
        transaction.setAmount(amount);
        cardTransactionRepository.save(transaction);
    }

    public void withdrawFromCard(String cardNumber,String expiryDate,String secCode,PaymentMethod paymentMethod
            ,BigDecimal amount)
    {
        Card card = cardRepository.findByCardNumber(cardNumber);
        validateCardDetails(card,expiryDate,secCode,paymentMethod,amount);
        if(amount.compareTo(amount) > 0)
        {
            throw new CustomException("Insufficient account balance to perform transaction",HttpStatus.BAD_REQUEST);
        }
        card.setAccountBalance(card.getAccountBalance().subtract(amount));
        card = cardRepository.save(card);

        CardTransaction transaction = new CardTransaction();
        transaction.setTransactionType(PaymentType.PAYMENT_FROM);
        transaction.setStatus(PaymentStatus.SUCCESS);
        transaction.setCard(card);
        transaction.setAmount(amount);
        cardTransactionRepository.save(transaction);
    }


}
