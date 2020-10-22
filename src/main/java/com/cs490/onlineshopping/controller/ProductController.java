package com.cs490.onlineshopping.controller;

import com.cs490.onlineshopping.admin.model.Product;
import com.cs490.onlineshopping.admin.model.Vendor;
import com.cs490.onlineshopping.admin.service.ProductService;
import com.cs490.onlineshopping.admin.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private VendorService vendorService;

    @GetMapping("/{vendorid}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable("vendorid") int vendor_id){
        try{

            Optional<Vendor> vendor = vendorService.findVendorById(vendor_id);
            if(vendor.isPresent()){
                List<Product> pro = productService.findByVendor(vendor.get());
                return new ResponseEntity<>(pro, HttpStatus.OK);
            }
            return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
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