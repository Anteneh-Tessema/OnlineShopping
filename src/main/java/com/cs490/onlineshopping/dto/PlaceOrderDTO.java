package com.cs490.onlineshopping.dto;

import java.util.List;

import com.cs490.onlineshopping.model.Address;

public class PlaceOrderDTO {

    private Address shippingAddress;   
    private Address billingAddress;
    private List<ItemListDTO> orderItems;
    private double totalPrice;
    private double shippingPrice;
    private double taxPrice;
    private double itemsPrice;
    
	public double getItemsPrice() {
		return itemsPrice;
	}


	public void setItemsPrice(double itemsPrice) {
		this.itemsPrice = itemsPrice;
	}


	public PlaceOrderDTO(Address shippingAddress, Address billingAddress, double totalPrice, double shippingPrice,
			double taxPrice, double itemsPrice) {
		super();
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.totalPrice = totalPrice;
		this.shippingPrice = shippingPrice;
		this.taxPrice = taxPrice;
		this.itemsPrice = itemsPrice;
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


	public List<ItemListDTO> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(List<ItemListDTO> orderItems) {
		this.orderItems = orderItems;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public double getShippingPrice() {
		return shippingPrice;
	}


	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}


	public double getTaxPrice() {
		return taxPrice;
	}


	public void setTaxPrice(double taxPrice) {
		this.taxPrice = taxPrice;
	}


	@Override
	public String toString() {
		return "billing" + this.getBillingAddress() + "\n" +
				"shipping" + this.getShippingAddress() + "\n" +
				"total" + this.getTotalPrice() + "\n" +
				"tax" + this.getTaxPrice() + "\n" +
				"shippingCost" + this.getShippingPrice();
	}
}
