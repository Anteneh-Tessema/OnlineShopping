package com.cs490.onlineshopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    
    public Page<Product> findAllByPage(Integer pageNumber, String keyword){
    	Pageable page = PageRequest.of(pageNumber, 5);
    	return productRepository.findByNameContaining(keyword, page);
    }

    public void saveProduct(Product product) throws Exception {
        productRepository.save(product);
    }
   

    public void deleteProduct(Product product) throws Exception {
        productRepository.delete(product);
    }

    public Page<Product> findByVendor(Vendor vendor, Integer pageNumber){
    	Pageable page = PageRequest.of(pageNumber, 5);
        return productRepository.findByVendor(vendor, page);
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }
}