package com.cs490.onlineshopping.model;
import javax.persistence.*;
import java.util.List;

@Entity
public class Vendor extends User{    

    @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Product> listProducts;

    private Boolean registrationFeeStatus;
   
    public Vendor() {
        super();
    }   

    public Boolean getRegistrationFeeStatus() {
        return registrationFeeStatus;
    }

    public void setRegistrationFeeStatus(Boolean registrationFeeStatus) {
        this.registrationFeeStatus = registrationFeeStatus;
    }
    
    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    @Override
    public String toString() {
        return "Vendor [vendorId=" + super.toString() + ", registrationFeeStatus="
                + registrationFeeStatus + ", role=" + this.getRole() + "]";
    }
}