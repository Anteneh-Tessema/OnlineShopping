package com.cs490.onlineshopping.service;

import com.cs490.onlineshopping.model.User;
import com.cs490.onlineshopping.model.Vendor;
import com.cs490.onlineshopping.dto.ItemListDTO;
import com.cs490.onlineshopping.dto.OrderDTO;
import com.cs490.onlineshopping.dto.OrderItemDTO;
import com.cs490.onlineshopping.dto.PaymentDTO;
import com.cs490.onlineshopping.dto.UserResponseDTO;
import com.cs490.onlineshopping.model.Order;
import com.cs490.onlineshopping.model.OrderItem;
import com.cs490.onlineshopping.model.Payment;
import com.cs490.onlineshopping.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentService paymentService;

	public List<Order> findByUser(User user) {
		return orderRepository.findByUser(user);
	}

	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	public Order saveOrder(Order order) throws Exception {
		return orderRepository.save(order);
	}

	public List<OrderDTO> findVendorOrders(Long id) {
		List<OrderDTO> orderDTOS = new ArrayList<>();
		List<Order> orders = orderRepository.findAll();

		orders.forEach(a -> {
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setId(a.getId());
			List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
			orderDTO.setShippingAddress(a.getShippingAddress());
			orderDTO.setBillingAddress(a.getBillingAddress());
			orderDTO.setUser(a.getUser());
			orderDTO.setOrder_created(a.getOrder_created());
			a.getOrderItems().forEach(b -> {
				if (b.getProduct().getVendor().getId() == id) {
					OrderItemDTO item = new OrderItemDTO();
					item.setProduct(b.getProduct());
					item.setPrice(b.getPrice());
					item.setQuantity(b.getQuantity());
					item.setId(b.getId());
					orderItemDTOS.add(item);
				}
			});
			PaymentDTO paymentdto = new PaymentDTO();
			Payment payment = paymentService.getPayment(a.getId());
			paymentdto.setUserId((payment.getUser()).getId());			
			paymentdto.setCardNumber(payment.getCardNumber());
			paymentdto.setStatusDescription(payment.getStatusDescription());
			paymentdto.setStatus(payment.getStatus());
			paymentdto.setMethod(payment.getMethod());
			orderDTO.setPayment(paymentdto);
			orderDTO.setListItemDTO(orderItemDTOS);
			orderDTOS.add(orderDTO);
		});

		return orderDTOS;
	}

	public List<Order> findAllOrders() {
		return orderRepository.findAll();
	}

}
