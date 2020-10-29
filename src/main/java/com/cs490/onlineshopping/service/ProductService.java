package com.cs490.onlineshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs490.onlineshopping.model.Product;
import com.cs490.onlineshopping.model.Vendor;
import com.cs490.onlineshopping.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    public void saveProduct(Product product) throws Exception {
        productRepository.save(product);
    }

    public void deleteProduct(Product product) throws Exception {
        productRepository.delete(product);
    }

    public List<Product> findByVendor(Vendor vendor){
        return productRepository.findByVendor(vendor);
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }
}