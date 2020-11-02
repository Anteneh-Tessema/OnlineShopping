package com.cs490.onlineshopping.api.request;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ProductRequest {
    private Long id;
    private String name;
    private String image;
    private String description;
    private Long vendor_id;
    private Double price;
    private Integer countInStock;
    @JsonProperty("isActive")
    private Boolean isActive;
    private List<CategoryRequest> categoriesId = new ArrayList<>();

    public ProductRequest() {
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

    public Long getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(Long vendor_id) {
        this.vendor_id = vendor_id;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<CategoryRequest> getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(List<CategoryRequest> categoriesId) {
        this.categoriesId = categoriesId;
    }
}
