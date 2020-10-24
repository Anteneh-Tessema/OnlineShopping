package com.cs490.onlineshopping.order.model;

import java.util.List;

import javax.persistence.*;

import com.cs490.onlineshopping.admin.model.Product;

@Entity
public class OrderItem {

	@Id
    @GeneratedValue
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
	
	private int quantity;
	
	@ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderItem(Order order, int quantity, Product product) {
		super();
		this.order = order;
		this.quantity = quantity;
		this.product = product;
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

}
