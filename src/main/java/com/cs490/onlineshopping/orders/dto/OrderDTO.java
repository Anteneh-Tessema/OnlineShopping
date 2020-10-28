package com.cs490.onlineshopping.orders.dto;

import java.time.OffsetTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.cs490.onlineshopping.admin.model.Address;
import com.cs490.onlineshopping.admin.model.User;
import com.cs490.onlineshopping.order.model.OrderItem;
import com.cs490.onlineshopping.order.model.Status;

public class OrderDTO {

    private int id;
    private Address shippingAddress;
    private Address billingAddress;
    private User user;
    private Status status;
    private OffsetTime order_created;
    private double total;
    private double shippingCost;
    private double tax;
    

	public OrderDTO(int id, Address shippingAddress, Address billingAddress, User user, Status status,
			OffsetTime order_created, double total, double shippingCost, double tax) {
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
	}


	public OrderDTO() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
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


	public OffsetTime getOrder_created() {
		return order_created;
	}


	public void setOrder_created(OffsetTime order_created) {
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

}
