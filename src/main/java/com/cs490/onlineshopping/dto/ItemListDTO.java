package com.cs490.onlineshopping.dto;

public class ItemListDTO {
	
	private Long productId;
	private int quantity;

	public ItemListDTO() {
		// TODO Auto-generated constructor stub
	}

	public ItemListDTO(Long productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
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
	

}
