package com.cs490.onlineshopping.admin.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vendor extends User{

    @OneToOne(cascade= {CascadeType.ALL})
    private Address address;

    @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Product> listProducts;

    private Boolean registrationFeeStatus;

    @Enumerated(EnumType.STRING)
    private Role role=Role.VENDOR;

    public Vendor() {
        super();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getRegistrationFeeStatus() {
        return registrationFeeStatus;
    }

    public void setRegistrationFeeStatus(Boolean registrationFeeStatus) {
        this.registrationFeeStatus = registrationFeeStatus;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    @Override
    public String toString() {
        return "Vendor [vendorId=" + super.toString() + ", address=" + address + ", registrationFeeStatus="
                + registrationFeeStatus + ", role=" + role + "]";
    }
}
