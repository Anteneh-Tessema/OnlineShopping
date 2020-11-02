package com.cs490.onlineshopping.dto;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.ArrayList;
import java.util.List;

import com.cs490.onlineshopping.model.Address;
import com.cs490.onlineshopping.model.Payment;
import com.cs490.onlineshopping.model.Status;
import com.cs490.onlineshopping.model.User;

public class OrderDTO {

	private Long id;
	private Address shippingAddress;
	private Address billingAddress;
	private User user;
	private Status status;
	private OffsetDateTime order_created;
	private double total;
	private double shippingCost;
	private double tax;
	private double price;
	private List<OrderItemDTO> listItemDTO;
	private PaymentDTO payment;

	public OrderDTO() {
		// TODO Auto-generated constructor stub
	}

	private OrderDTO(Long id, Address shippingAddress, Address billingAddress, User user, Status status,
			OffsetDateTime order_created, double total, double shippingCost, double tax, double price,
			List<OrderItemDTO> listItemDTO, PaymentDTO payment) {
		super();
		this.id = id;
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.user = user;
		this.status = status;
		this.order_created = order_created;
		this.total = total;
		this.shippingCost = shippingCost;
		this.tax = tax;
		this.price = price;
		this.listItemDTO = listItemDTO;
		this.payment = payment;
	}

	public PaymentDTO getPayment() {
		return payment;
	}

	public void setPayment(PaymentDTO payment) {
		this.payment = payment;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public OffsetDateTime getOrder_created() {
		return order_created;
	}

	public void setOrder_created(OffsetDateTime order_created) {
		this.order_created = order_created;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public List<OrderItemDTO> getListItemDTO() {
		return listItemDTO;
	}

	public void setListItemDTO(List<OrderItemDTO> listItemDTO) {
		this.listItemDTO = listItemDTO;
	}
}
