package com.cs490.onlineshopping.repository;

import com.cs490.onlineshopping.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Repository
@Transactional
public interface CardRepository extends JpaRepository<Card, BigInteger> {

    Card findByCardNumber(String cardNumber);
    
}
