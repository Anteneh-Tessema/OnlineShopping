package com.cs490.onlineshopping.order.service;

import com.cs490.onlineshopping.admin.model.Product;
import com.cs490.onlineshopping.admin.model.Vendor;
import com.cs490.onlineshopping.admin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private ProductRepository productRepository;

}
