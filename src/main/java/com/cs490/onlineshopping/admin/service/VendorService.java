package com.cs490.onlineshopping.admin.service;

import com.cs490.onlineshopping.admin.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;
}