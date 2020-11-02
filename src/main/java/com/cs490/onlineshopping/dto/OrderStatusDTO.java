package com.cs490.onlineshopping.dto;

public class OrderStatusDTO {
	
	private Long orderId;
	private int status;

	public OrderStatusDTO() {
		// TODO Auto-generated constructor stub
	}

	public OrderStatusDTO(Long orderId, int status) {
		super();
		this.orderId = orderId;
		this.status = status;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	


}
