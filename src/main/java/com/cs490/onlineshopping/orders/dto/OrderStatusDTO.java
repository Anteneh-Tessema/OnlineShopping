package com.cs490.onlineshopping.orders.dto;

public class OrderStatusDTO {
	
	private int orderId;
	private int status;

	public OrderStatusDTO() {
		// TODO Auto-generated constructor stub
	}

	public OrderStatusDTO(int orderId, int status) {
		super();
		this.orderId = orderId;
		this.status = status;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	


}
