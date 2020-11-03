package com.cs490.onlineshopping.model;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    
    @OneToOne(cascade= {CascadeType.ALL})
    private Address shippingAddress;
    
    @OneToOne(cascade= {CascadeType.ALL})
    private Address billingAddress;
    
    //TODO paymentId
    
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    
    @OneToMany(mappedBy="order")
    private List<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Column(name = "order_created")
    private OffsetDateTime order_created;
    
	private double total;
    private double shippingCost;
    private double tax;
    private double price;
    
	public Order() {
		super();
	}

	public Order(Address shippingAddress, Address billingAddress, User user,
			Status status, OffsetDateTime order_created, double total, double shippingCost,
			double tax, double price) {
		super();
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.user = user;
		this.status = status;
		this.order_created = order_created;
		this.total = total;
		this.shippingCost = shippingCost;
		this.tax = tax;
		this.price = price;
	}
	

	public Long getId() {
		return this.id;
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

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
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
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String toString() {
		return "billing" + this.getBillingAddress().getId() + "/n" +
				"shipping" + this.getShippingAddress() + "/n" +
				"user" + this.getUser().getUsername() + "/n" +
				"status" + this.getStatus() + "/n" +
				"total" + this.getTotal() + "/n" +
				"tax" + this.getTax() + "/n" +
				"price" + this.getPrice() + "/n" +
				"billing" + this.getBillingAddress() + "/n" +
				"shippingCost" + this.getShippingCost();
	}
    
}
