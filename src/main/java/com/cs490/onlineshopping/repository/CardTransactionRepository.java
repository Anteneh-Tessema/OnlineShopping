package com.cs490.onlineshopping.repository;

import com.cs490.onlineshopping.model.CardTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CardTransactionRepository extends JpaRepository<CardTransaction, BigInteger> {
}
