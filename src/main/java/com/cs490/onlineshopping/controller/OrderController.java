package com.cs490.onlineshopping.controller;

import com.cs490.onlineshopping.admin.model.Product;
import com.cs490.onlineshopping.admin.model.User;
import com.cs490.onlineshopping.admin.service.ProductService;
import com.cs490.onlineshopping.admin.service.UserService;
import com.cs490.onlineshopping.order.model.Order;
import com.cs490.onlineshopping.order.model.OrderItem;
import com.cs490.onlineshopping.order.model.Status;
import com.cs490.onlineshopping.order.service.OrderItemService;
import com.cs490.onlineshopping.order.service.OrderService;
import com.cs490.onlineshopping.orders.dto.ItemListDTO;
import com.cs490.onlineshopping.orders.dto.OrderDTO;
import com.cs490.onlineshopping.orders.dto.OrderStatusDTO;
import com.cs490.onlineshopping.orders.dto.PlaceOrderDTO;

import org.springframework.beans.BeanUtils;
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
    
    @Autowired
    private OrderItemService orderItemService;

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
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("orderid") int orderid){
        try{
            Optional<Order> order = orderService.findById(orderid);
            if(order.isPresent()){
            	OrderDTO orderDTO = new OrderDTO();
            	BeanUtils.copyProperties(order.get(), orderDTO);
            	System.out.println(orderDTO.toString());
                return new ResponseEntity<>(orderDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(new OrderDTO(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new OrderDTO() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Boolean> placeOrder(@RequestBody PlaceOrderDTO order) {
        try {
        	System.out.println(order.getItemList().get(0).toString());
        	Optional<User> userOrder = userService.findById(order.getUser());
        	if (userOrder.isPresent()) {
              Order newOrder = orderService.saveOrder(new Order(
            		order.getShippingAddress(),
            		order.getBillingAddress(),
            		userOrder.get(),
            		Status.RECEIVED,
            		OffsetTime.now(),
            		order.getTotal(),
            		order.getShippingCost(),
          			order.getTax()
            		  ));
              	for (int i = 0; i < order.getItemList().size(); i++) {
              		ItemListDTO item = order.getItemList().get(i);
              		Optional<Product> product = productService.findById(item.getProductId());
    	          	orderItemService.saveItemOrder(new OrderItem(newOrder, item.getQuantity(), product.get())); 
    	          	product.get().setCountInStock(product.get().getCountInStock()-1);
    	          	productService.saveProduct(product.get());
              	}     
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
    @PostMapping("/status")
    public ResponseEntity<Boolean> changeOrderStatus(@RequestBody OrderStatusDTO ordStatus) {
        try {
        	Optional<Order> order = orderService.findById(ordStatus.getOrderId());
        	if (order.isPresent()) {
        		System.out.println(Status.valueOf(ordStatus.getStatus()));
        		order.get().setStatus(Status.valueOf(ordStatus.getStatus()));
        		orderService.saveOrder(order.get());
                return new ResponseEntity<>(true,HttpStatus.OK);
        	}
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
        	System.out.println(e);
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}