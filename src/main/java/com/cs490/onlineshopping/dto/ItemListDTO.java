package com.cs490.onlineshopping.dto;

public class ItemListDTO {
	
	private Long productId;
	private int quantity;
	private double price;

	public ItemListDTO() {
		// TODO Auto-generated constructor stub
	}

	public ItemListDTO(Long productId, int quantity, double price) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

}
