package com.cs490.onlineshopping.repository;

import com.cs490.onlineshopping.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT o FROM OrderItem o WHERE o.product.id = ?1 and o.order.status = 'RECEIVED'")
    List<OrderItem> findOrderItem(Long productid);

}
