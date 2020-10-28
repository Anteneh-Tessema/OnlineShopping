package com.cs490.onlineshopping.orders.dto;

import java.math.BigDecimal;
import java.time.OffsetTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.cs490.onlineshopping.admin.model.Address;
import com.cs490.onlineshopping.admin.model.User;
import com.cs490.onlineshopping.order.model.OrderItem;
import com.cs490.onlineshopping.order.model.Status;

public class PlaceOrderDTO {

    private Address shippingAdress;   
    private Address billingAddress;
    //TODO paymentId
    private int user;  
//    private List<OrderItem> orderItems;
    private double total;
    private double shippingCost;
    private double tax;
	public PlaceOrderDTO(Address shippingAdress, Address billingAddress, int user, double total, double shippingCost,
			double tax) {
		super();
		this.shippingAdress = shippingAdress;
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
	public Address getShippingAdress() {
		return shippingAdress;
	}
	public void setShippingAdress(Address shippingAdress) {
		this.shippingAdress = shippingAdress;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
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
				"shipping" + this.getBillingAddress() + "\n" +
				"user" + this.getUser() + "\n" +
				"total" + this.getTotal() + "\n" +
				"tax" + this.getTax() + "\n" +
				"shippingCost" + this.getShippingCost();
	}
    
}
