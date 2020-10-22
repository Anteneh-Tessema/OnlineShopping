package com.cs490.onlineshopping.admin.service;

import com.cs490.onlineshopping.admin.model.Vendor;
import com.cs490.onlineshopping.admin.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public Optional<Vendor> findVendorById(int vendorId){
        return vendorRepository.findById(vendorId);
    }
}