package com.cs490.onlineshopping.dto;

public class ProductOrderDTO {
	
	    private Long id;
	    private String name;
	    private String image;
	    private String description; 
	    private Double price;
	    private Boolean isActive = true;
	    private String category;

	public ProductOrderDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProductOrderDTO(Long id, String name, String image, String description, Double price, Boolean isActive, String category) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.price = price;
		this.isActive = isActive;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
