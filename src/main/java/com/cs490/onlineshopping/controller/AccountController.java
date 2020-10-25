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
import com.cs490.onlineshopping.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AccountController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/registerClient")
    public ResponseEntity<UserResponse> saveClient(@RequestBody UserRequest userRequest) {
        try {
                Optional<User> user = userService.findByUsername(userRequest.getUsername());
                if(!user.isPresent()){
                    Address addresSaved = addressService.saveAddress(new Address(userRequest.getState(),
                            userRequest.getCity(),
                            userRequest.getZipCode(),
                            userRequest.getEmail(),
                            userRequest.getPhoneNumber()));

                    Client clientSaved = clientService.saveClient(new Client(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getUsername(), passwordEncoder.encode(userRequest.getPassword()), addresSaved));

                    return new ResponseEntity<>(new UserResponse(clientSaved.getId(), clientSaved.getFirstName(), clientSaved.getLastName(),clientSaved.getUsername(), clientSaved.getPassword()), HttpStatus.OK);
                }
            return new ResponseEntity<>(new UserResponse("Username already exists"), HttpStatus.OK);
        }
            catch (Exception ex) {
            return new ResponseEntity<>(new UserResponse("Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registerVendor")
    public ResponseEntity<UserResponse> saveVendor(@RequestBody UserRequest userRequest) {
        try {
            Optional<User> user = userService.findByUsername(userRequest.getUsername());
            if(!user.isPresent()) {
                Address addresSaved = addressService.saveAddress(new Address(userRequest.getState(),
                        userRequest.getCity(),
                        userRequest.getZipCode(),
                        userRequest.getEmail(),
                        userRequest.getPhoneNumber()));

                Vendor vendorSaved = vendorService.saveVendor(new Vendor(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getUsername(), passwordEncoder.encode(userRequest.getPassword()), addresSaved));

                return new ResponseEntity<>(new UserResponse(vendorSaved.getId(), vendorSaved.getFirstName(), vendorSaved.getLastName(),vendorSaved.getUsername(), vendorSaved.getPassword()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new UserResponse("Username already exists"), HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new UserResponse("Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
