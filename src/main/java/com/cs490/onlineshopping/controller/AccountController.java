package com.cs490.onlineshopping.controller;

import com.cs490.onlineshopping.admin.model.Address;
import com.cs490.onlineshopping.admin.model.Client;
import com.cs490.onlineshopping.admin.model.User;
import com.cs490.onlineshopping.admin.model.Vendor;
import com.cs490.onlineshopping.admin.service.AddressService;
import com.cs490.onlineshopping.admin.service.ClientService;
import com.cs490.onlineshopping.admin.service.UserService;
import com.cs490.onlineshopping.admin.service.VendorService;
import com.cs490.onlineshopping.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/users")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private VendorService vendorService;

    @PostMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/registerClient")
    public ResponseEntity<User> saveClient(@RequestBody UserRequest userRequest) {
        try {
                Address addresSaved = addressService.saveAddress(new Address(userRequest.getState(),
                        userRequest.getCity(),
                        userRequest.getZipCode(),
                        userRequest.getEmail(),
                        userRequest.getPhoneNumber()));

                Client clientSaved = clientService.saveClient(new Client(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getUsername(), userRequest.getPassword(), addresSaved));

                return new ResponseEntity<>(clientSaved, HttpStatus.OK);
        }
            catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registerVendor")
    public ResponseEntity<User> saveVendor(@RequestBody UserRequest userRequest) {
        try {
            Address addresSaved = addressService.saveAddress(new Address(userRequest.getState(),
                    userRequest.getCity(),
                    userRequest.getZipCode(),
                    userRequest.getEmail(),
                    userRequest.getPhoneNumber()));

            Vendor clientSaved = vendorService.saveVendor(new Vendor(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getUsername(), userRequest.getPassword(), addresSaved));

            return new ResponseEntity<>(clientSaved, HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
