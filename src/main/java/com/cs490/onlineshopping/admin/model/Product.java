package com.cs490.onlineshopping.admin.model;

import java.util.List;

import javax.persistence.*;

import com.cs490.onlineshopping.order.model.OrderItem;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String image;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vendor_id", nullable = false)
    private Vendor vendor;
//    category todo should be table or enum

    private Double price;
    private Integer countInStock;

    public int getId() {
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
