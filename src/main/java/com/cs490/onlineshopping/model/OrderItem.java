package com.cs490.onlineshopping.model;

import javax.persistence.*;

import org.hibernate.query.criteria.LiteralHandlingMode;

@Entity
public class OrderItem {

	@Id
    @GeneratedValue
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
	
	private int quantity;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
	
	private double price;

	public OrderItem() {
		super();
	}
	
	public Long getId() {
		return this.id;
	}

	public OrderItem(Order order, int quantity, Product product, double price) {
		super();
		this.order = order;
		this.quantity = quantity;
		this.product = product;
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	
}
