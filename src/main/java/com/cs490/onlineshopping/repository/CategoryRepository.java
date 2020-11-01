package com.cs490.onlineshopping.repository;

import com.cs490.onlineshopping.model.Card;
import com.cs490.onlineshopping.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}