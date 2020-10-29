package com.cs490.onlineshopping.dto;

import java.util.List;

import com.cs490.onlineshopping.model.Address;

public class PlaceOrderDTO {

    private Address shippingAddress;   
    private Address billingAddress;
    //TODO paymentId
    private Long user;  
    private List<ItemListDTO> itemList;
    private double total;
    private double shippingCost;
    private double tax;
	public PlaceOrderDTO(Address shippingAddress, Address billingAddress, Long user, double total, double shippingCost,
			double tax) {
		super();
		this.shippingAddress = shippingAddress;
		this.billingAddress = billingAddress;
		this.user = user;
		this.total = total;
		this.shippingCost = shippingCost;
		this.tax = tax;
	}
	public PlaceOrderDTO() {
		super();
		// TODO Auto-generated constructor stub
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
	public Long getUser() {
		return user;
	}
	public void setUser(Long user) {
		this.user = user;
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
    
	@Override
	public String toString() {
		return "billing" + this.getBillingAddress() + "\n" +
				"shipping" + this.getShippingAddress() + "\n" +
				"user" + this.getUser() + "\n" +
				"total" + this.getTotal() + "\n" +
				"tax" + this.getTax() + "\n" +
				"shippingCost" + this.getShippingCost();
	}
	public List<ItemListDTO> getItemList() {
		return itemList;
	}
	public void setItemList(List<ItemListDTO> itemList) {
		this.itemList = itemList;
	}
    
}
