package com.cs490.onlineshopping.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.cs490.onlineshopping.dto.ItemListDTO;
import com.cs490.onlineshopping.dto.MakePaymentDTO;
import com.cs490.onlineshopping.dto.OrderDTO;
import com.cs490.onlineshopping.dto.OrderItemDTO;
import com.cs490.onlineshopping.dto.OrderStatusDTO;
import com.cs490.onlineshopping.dto.PaymentDTO;
import com.cs490.onlineshopping.dto.PlaceOrderDTO;
import com.cs490.onlineshopping.model.Admin;
import com.cs490.onlineshopping.model.Client;
import com.cs490.onlineshopping.model.Order;
import com.cs490.onlineshopping.model.OrderItem;
import com.cs490.onlineshopping.model.Payment;
import com.cs490.onlineshopping.model.PaymentMethod;
import com.cs490.onlineshopping.model.Product;
import com.cs490.onlineshopping.model.Role;
import com.cs490.onlineshopping.model.Status;
import com.cs490.onlineshopping.model.User;
import com.cs490.onlineshopping.model.Vendor;
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
@RequestMapping("/api/orders")
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

	@GetMapping("/myorders")	
	public ResponseEntity<List<OrderDTO>> getAllOrdersByUser(HttpServletRequest req) {

		try {
			User user = userService.whoami(req);
			List<Order> order = orderService.findByUser(user);
			List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
			for (int i = 0; i < order.size(); i++) {

				OrderDTO orderDTO = new OrderDTO();
				BeanUtils.copyProperties(order.get(i), orderDTO);
				orderDTO.setListItemDTO(new ArrayList<OrderItemDTO>());
				List<OrderItem> listItems = orderItemService.findByOrder(order.get(i));
				for (int j = 0; j < listItems.size(); j++) {
					OrderItemDTO target = new OrderItemDTO();
					target.setId(listItems.get(i).getId());
					target.setProduct(listItems.get(i).getProduct());
					target.setQuantity(listItems.get(i).getQuantity());
					target.setPrice(listItems.get(i).getPrice());
					orderDTO.getListItemDTO().add(target);
				}
				orderDTOs.add(orderDTO);
			}
			return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * 
	 * @author Amit Bhattarai
	 *
	 */
	@GetMapping()	
	public ResponseEntity<List<OrderDTO>> getAllOrders() {

		try {
			
			List<Order> order = orderService.findAllOrders();
			List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
			for (int i = 0; i < order.size(); i++) {

				OrderDTO orderDTO = new OrderDTO();
				BeanUtils.copyProperties(order.get(i), orderDTO);
				orderDTO.setListItemDTO(new ArrayList<OrderItemDTO>());
				List<OrderItem> listItems = orderItemService.findByOrder(order.get(i));
				for (int j = 0; j < listItems.size(); j++) {
					OrderItemDTO target = new OrderItemDTO();
					target.setId(listItems.get(i).getId());
					target.setProduct(listItems.get(i).getProduct());
					target.setQuantity(listItems.get(i).getQuantity());
					target.setPrice(listItems.get(i).getPrice());
					orderDTO.getListItemDTO().add(target);
				}
				orderDTOs.add(orderDTO);
			}
			return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @author Amit Bhattarai
	 *
	 */
	@GetMapping("/vendors")	
	public ResponseEntity<List<OrderDTO>> getAllOrdersByVendor(HttpServletRequest req) {
		try {
			Vendor user = (Vendor) userService.whoami(req);
			if (user.getRole() != Role.VENDOR)
				throw new IllegalArgumentException("Vendor cannot be verified");

			List<OrderDTO> order = orderService.findVendorOrders(user.getId());
			
			
			return new ResponseEntity<>(order, HttpStatus.OK);
		}

		catch (Exception ex) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{orderid}")	
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable("orderid") Long orderid) {
		try {
			Optional<Order> order = orderService.findById(orderid);
			if (order.isPresent()) {
				OrderDTO orderDTO = new OrderDTO();
				BeanUtils.copyProperties(order.get(), orderDTO);
				orderDTO.setListItemDTO(new ArrayList<OrderItemDTO>());
				List<OrderItem> listItems = orderItemService.findByOrder(order.get());
				for (int i = 0; i < listItems.size(); i++) {
					OrderItemDTO target = new OrderItemDTO();
					target.setId(listItems.get(i).getId());
					target.setProduct(listItems.get(i).getProduct());
					target.setQuantity(listItems.get(i).getQuantity());
					target.setPrice(listItems.get(i).getPrice());
					orderDTO.getListItemDTO().add(target);
				}
				PaymentDTO paymentdto = new PaymentDTO();
				Payment payment = paymentService.getPayment(orderid);
				paymentdto.setUserId((payment.getUser()).getId());
				paymentdto.setAmount(payment.getAmount());
				paymentdto.setCardNumber(payment.getCardNumber());
				paymentdto.setStatusDescription(payment.getStatusDescription());
				paymentdto.setStatus(payment.getStatus());
				paymentdto.setMethod(payment.getMethod());
				orderDTO.setPayment(paymentdto);
				return new ResponseEntity<>(orderDTO, HttpStatus.OK);
			}
			return new ResponseEntity<>(new OrderDTO(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<>(new OrderDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping()
	public ResponseEntity<OrderDTO> placeOrder(@RequestBody PlaceOrderDTO cart, HttpServletRequest req) {
		try {
			User user = userService.whoami(req);
			System.out.println(cart.getOrderItems().size());
			if (user.getId() != null) {
				Order newOrder = orderService.saveOrder(new Order(cart.getShippingAddress(), cart.getBillingAddress(),
						user, Status.RECEIVED, OffsetDateTime.now(), cart.getTotalPrice(), cart.getShippingPrice(),
						cart.getTaxPrice(), cart.getItemsPrice()));
				List<OrderItem> orderItems = new ArrayList<>();
				for (int i = 0; i < cart.getOrderItems().size(); i++) {
					ItemListDTO item = cart.getOrderItems().get(i);
					
					Optional<Product> product = productService.findById(item.getProductId());
					System.out.println(item.getPrice());
					OrderItem orderItem = new OrderItem(newOrder, item.getQuantity(), product.get(), item.getPrice());
					orderItemService
							.saveItemOrder(new OrderItem(newOrder, item.getQuantity(), product.get(), item.getPrice()));
					product.get().setCountInStock(product.get().getCountInStock() - 1);
					orderItems.add(orderItem);
					productService.saveProduct(product.get());
				}
				newOrder.setOrderItems(orderItems);
				

				// Payment
				MakePaymentDTO paymentDTO = new MakePaymentDTO();
				paymentDTO.setCustomerUserId(user.getId());
				paymentDTO.setAmount(BigDecimal.valueOf(cart.getTotalPrice()));
				paymentDTO.setCardNumber(cart.getPaymentMethod().getCardNumber());
				paymentDTO.setCardExpiryDate(cart.getPaymentMethod().getExpiryDate());
				paymentDTO.setSecurityCode(cart.getPaymentMethod().getCvc());
				paymentDTO.setPaymentMethod(PaymentMethod.valueOf(cart.getPaymentMethod().getMethod().toUpperCase()));
				paymentDTO.setOrderId(newOrder.getId());
				paymentService.payForItems(paymentDTO);

				return getOrderById(newOrder.getId());
			}
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// TODO Change status of order
	@PostMapping("/status")	
	public ResponseEntity<Boolean> changeOrderStatus(@RequestBody OrderStatusDTO ordStatus) {
		try {
			Optional<Order> order = orderService.findById(ordStatus.getOrderId());
			if (order.isPresent()) {
				order.get().setStatus(Status.valueOf(ordStatus.getStatus()));
				orderService.saveOrder(order.get());
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}