package com.cs490.onlineshopping.controller;

import com.cs490.onlineshopping.admin.model.Address;
import com.cs490.onlineshopping.admin.model.Client;
import com.cs490.onlineshopping.admin.model.Product;
import com.cs490.onlineshopping.admin.model.User;
import com.cs490.onlineshopping.admin.model.Vendor;
import com.cs490.onlineshopping.admin.service.ProductService;
import com.cs490.onlineshopping.admin.service.UserService;
import com.cs490.onlineshopping.admin.service.VendorService;
import com.cs490.onlineshopping.order.model.Order;
import com.cs490.onlineshopping.order.model.OrderItem;
import com.cs490.onlineshopping.order.model.Status;
import com.cs490.onlineshopping.order.service.OrderService;
import com.cs490.onlineshopping.orders.dto.PlaceOrderDTO;
import com.cs490.onlineshopping.payments.dto.MakePaymentDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetTime;
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

    @GetMapping("/user/{userid}")
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
            System.out.println(order);
            if(order.isPresent()){
                new ResponseEntity<>(order.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Order(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new Order() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Boolean> placeOrder(@RequestBody PlaceOrderDTO order) {
        try {
//        	System.out.println(order.toString());
        	Optional<User> userOrder = userService.findById(order.getUser());
        	if (userOrder.isPresent()) {
//              orderService.saveOrder(new Order(
//            		  new ArrayList<Product>() {}, order.getShippingAdress(), order.getBillingAddress(), userOrder,
//          			List<OrderItem> orderItems, Status status, OffsetTime order_created, double total, double shippingCost,
//          			double tax
//            		  ));
                return new ResponseEntity<>(true,HttpStatus.OK);
        	}
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
        	System.out.println(e);
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //TODO Change status of order
}