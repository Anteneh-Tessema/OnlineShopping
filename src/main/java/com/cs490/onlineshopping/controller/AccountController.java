package com.cs490.onlineshopping.controller;

import com.cs490.onlineshopping.admin.model.Address;
import com.cs490.onlineshopping.admin.model.Client;
import com.cs490.onlineshopping.admin.model.User;
import com.cs490.onlineshopping.admin.service.AddressService;
import com.cs490.onlineshopping.admin.service.ClientService;
import com.cs490.onlineshopping.admin.service.UserService;
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

    @PostMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/registerClient")
    public ResponseEntity<User> saveEndUser(@RequestBody UserRequest userRequest) {
        try {
//            User user = new User();
//            user.setFirstName(userRequest.getFirstName());
//            user.setLastName(userRequest.getLastName());
//            user.setUsername(userRequest.getUsername());
//            user.setPassword(userRequest.getPassword());
//
//            User userSaved = userService.saveUser(user);

//            if(userSaved != null){
                Address addresSaved = addressService.saveAddress(new Address(userRequest.getState(),
                        userRequest.getCity(),
                        userRequest.getZipCode(),
                        userRequest.getEmail(),
                        userRequest.getPhoneNumber()));

                Client clientSaved = clientService.saveClient(new Client(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getUsername(), userRequest.getPassword(), addresSaved));

                return new ResponseEntity<>(clientSaved, HttpStatus.OK);

//            }
//            return new ResponseEntity<>(new Client(), HttpStatus.BAD_REQUEST);
        }
            catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
