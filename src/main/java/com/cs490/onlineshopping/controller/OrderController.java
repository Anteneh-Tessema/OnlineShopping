package com.cs490.onlineshopping.controller;

import com.cs490.onlineshopping.admin.model.Client;
import com.cs490.onlineshopping.admin.model.Product;
import com.cs490.onlineshopping.admin.model.User;
import com.cs490.onlineshopping.admin.model.Vendor;
import com.cs490.onlineshopping.admin.service.ProductService;
import com.cs490.onlineshopping.admin.service.UserService;
import com.cs490.onlineshopping.admin.service.VendorService;
import com.cs490.onlineshopping.order.model.Order;
import com.cs490.onlineshopping.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/getOrdersByUser/{userid}")
    public ResponseEntity<List<Order>> getAllOrdersByUser(@PathVariable("userid") int user_id){
        try{

            Optional<User> user = userService.findById(user_id);
            if(user.isPresent()){
                List<Order> order = orderService.findByUser(user.get());
                return new ResponseEntity<>(order, HttpStatus.OK);
            }
            return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new ArrayList<>() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderid}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderid") int orderid){
        try{
            Optional<Order> order = orderService.findById(orderid);
            if(order.isPresent()){
                new ResponseEntity<>(order.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Order(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new Order() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> placeOrder(@RequestBody Order order) {
        try {
            orderService.saveOrder(order);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}