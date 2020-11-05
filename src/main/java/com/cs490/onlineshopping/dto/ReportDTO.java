package com.cs490.onlineshopping.dto;

public class ReportDTO {
	private String id;
	private String name;	
	private String description;
	private String vendor_username;
	private String countInStock;
	private String price;
	private String image;
	private String price_earned;
    private String sold_No;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public String getPrice_earned() {return price_earned;	}
	public void setPrice_earned(String price_earned) {this.price_earned = price_earned;	}
	public String getSold_No() {return sold_No;	}
	public void setSold_No(String sold_No) {this.sold_No = sold_No;	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getVendor_username() {
		return vendor_username;
	}
	public void setVendor_username(String vendor_username) {
		this.vendor_username = vendor_username;
	}
	public String getCountInStock() {
		return countInStock;
	}
	public void setCountInStock(String countInStock) {
		this.countInStock = countInStock;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
