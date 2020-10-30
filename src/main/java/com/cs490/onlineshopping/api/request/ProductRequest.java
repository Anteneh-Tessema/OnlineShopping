package com.cs490.onlineshopping.api.request;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequest {
    private Long id;
    private String name;
    private String image;
    private String description;
    private Integer vendor_id;
    private Double price;
    private Integer countInStock;
    @JsonProperty("active")
    private Boolean active;

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

    public Integer getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(Integer vendor_id) {
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
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
