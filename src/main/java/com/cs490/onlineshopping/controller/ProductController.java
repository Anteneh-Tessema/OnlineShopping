package com.cs490.onlineshopping.controller;

import com.cs490.onlineshopping.admin.model.Product;
import com.cs490.onlineshopping.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            List<Product> pro = productService.findAll();
            return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new ArrayList<>() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<Boolean> saveProduct(@RequestBody Product product) {
        try {
            productService.saveProduct(product);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}