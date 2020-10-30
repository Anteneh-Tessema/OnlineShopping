package com.cs490.onlineshopping.dto;

import org.springframework.beans.BeanUtils;

import com.cs490.onlineshopping.model.Product;

public class OrderItemDTO {
	
	private Long id;
	private int quantity;
    private ProductOrderDTO product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductOrderDTO getProduct() {
		return product;
	}

	public void setProduct(Product product) {
//		this.product = product;
		this.product = new ProductOrderDTO();
		BeanUtils.copyProperties(product, this.product);
	}

	public OrderItemDTO(Long id, int quantity, Product product) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = new ProductOrderDTO();
		BeanUtils.copyProperties(product, this.product);
	}

	public OrderItemDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return this.getId()+"\n"+this.getQuantity()+"\n"+this.getProduct();
	}

}
