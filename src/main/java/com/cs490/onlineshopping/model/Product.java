package com.cs490.onlineshopping.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String image;
    private String description;
    private Boolean isActive = false;    

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;
	
	@ManyToOne
	@JoinColumn(name="category_id", nullable=false)
	private Category category;
	
    private Double price;
    private Integer countInStock;
   
    
    public Product(String name, String image, String description, Vendor vendor, Double price, Integer countInStock, Category category) {
		super();
		this.name = name;
		this.image = image;
		this.description = description;
		this.vendor = vendor;
		this.price = price;
		this.countInStock = countInStock;
		this.category = category;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCountInStock() {
        return countInStock;
    }

    public void setCountInStock(Integer countInStock) {
        this.countInStock = countInStock;
    }

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}