package com.cs490.onlineshopping.model;

import javax.persistence.Entity;

@Entity
public class Product {
    private Long id;
    private String name;
    private String image;
    private String description;
//    vendorId	Long todo should be reference to user object
//    category todo should be table or enum

    private Double price;
    private Integer countInStock;

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
}
