package com.cs490.onlineshopping.orders.dto;

public class ItemListDTO {
	
	private int productId;
	private int quantity;

	public ItemListDTO() {
		// TODO Auto-generated constructor stub
	}

	public ItemListDTO(int productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
