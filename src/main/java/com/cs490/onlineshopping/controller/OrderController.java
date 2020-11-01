package com.cs490.onlineshopping.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cs490.onlineshopping.dto.ItemListDTO;
import com.cs490.onlineshopping.dto.MakePaymentDTO;
import com.cs490.onlineshopping.dto.OrderDTO;
import com.cs490.onlineshopping.dto.OrderItemDTO;
import com.cs490.onlineshopping.dto.OrderStatusDTO;
import com.cs490.onlineshopping.dto.PlaceOrderDTO;
import com.cs490.onlineshopping.model.Order;
import com.cs490.onlineshopping.model.OrderItem;
import com.cs490.onlineshopping.model.PaymentMethod;
import com.cs490.onlineshopping.model.Product;
import com.cs490.onlineshopping.model.Status;
import com.cs490.onlineshopping.model.User;
import com.cs490.onlineshopping.service.OrderItemService;
import com.cs490.onlineshopping.service.OrderService;
import com.cs490.onlineshopping.service.PaymentService;
import com.cs490.onlineshopping.service.ProductService;
import com.cs490.onlineshopping.service.UserService;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderItemService orderItemService;
    
    @Autowired
    private PaymentService paymentService;

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAllOrdersByUser(HttpServletRequest req){

        try{
        	User user = userService.whoami(req);
                List<Order> order = orderService.findByUser(user);
                List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
                for (int i = 0; i < order.size(); i++) {
                	OrderDTO target = new OrderDTO(); 
                	BeanUtils.copyProperties(order.get(i), target);
                	orderDTOs.add(target);
                }
                return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new ArrayList<>() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderid}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("orderid") Long orderid){
        try{
            Optional<Order> order = orderService.findById(orderid);
            if(order.isPresent()){
            	OrderDTO orderDTO = new OrderDTO();
            	BeanUtils.copyProperties(order.get(), orderDTO);
            	orderDTO.setListItemDTO(new ArrayList<OrderItemDTO>());
            	List<OrderItem> listItems = orderItemService.findByOrder(order.get());
            	for(int i = 0; i < listItems.size(); i++) {
            		OrderItemDTO target = new OrderItemDTO();
            		target.setId(listItems.get(i).getId());
            		target.setProduct(listItems.get(i).getProduct());
            		target.setQuantity(listItems.get(i).getQuantity());
            		orderDTO.getListItemDTO().add(target);
            	}
                return new ResponseEntity<>(orderDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(new OrderDTO(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(new OrderDTO() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Boolean> placeOrder(@RequestBody PlaceOrderDTO order, HttpServletRequest req) {
        try {
        	User user = userService.whoami(req);
        	if (user.getId() != null) {
              Order newOrder = orderService.saveOrder(new Order(
            		order.getShippingAddress(),
            		order.getBillingAddress(),
            		user,
            		Status.RECEIVED,
            		OffsetDateTime.now(),
            		order.getTotalPrice(),
            		order.getShippingPrice(),
          			order.getTaxPrice(),
          			order.getItemsPrice()
            		  ));
              	for (int i = 0; i < order.getOrderItems().size(); i++) {
              		ItemListDTO item = order.getOrderItems().get(i);
              		Optional<Product> product = productService.findById(item.getProductId());
    	          	orderItemService.saveItemOrder(new OrderItem(newOrder, item.getQuantity(), product.get())); 
    	          	product.get().setCountInStock(product.get().getCountInStock()-1);
    	          	productService.saveProduct(product.get());
              	}     
              	
              	//Payment
              	MakePaymentDTO paymentDTO = new MakePaymentDTO();
              	paymentDTO.setCustomerUserId(user.getId());
              	paymentDTO.setAmount(BigDecimal.valueOf(order.getTotalPrice()));
              	paymentDTO.setCardNumber(order.getPaymentMethod().getCardNumber());
              	paymentDTO.setCardExpiryDate(order.getPaymentMethod().getExpiryDate());
              	paymentDTO.setSecurityCode(order.getPaymentMethod().getCvc());
              	paymentDTO.setPaymentMethod(PaymentMethod.valueOf(order.getPaymentMethod().getMethod().toUpperCase()));
              	paymentDTO.setOrderId(newOrder.getId());
              	paymentService.payForItems(paymentDTO);
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
        		order.get().setStatus(Status.valueOf(ordStatus.getStatus()));
        		orderService.saveOrder(order.get());
                return new ResponseEntity<>(true,HttpStatus.OK);
        	}
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}