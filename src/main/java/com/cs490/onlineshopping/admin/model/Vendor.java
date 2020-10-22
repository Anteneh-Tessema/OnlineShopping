package com.cs490.onlineshopping.admin.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vendor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

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

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "Vendor [vendorId=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
                + userName + ", password=" + password + ", address=" + address + ", registrationFeeStatus="
                + registrationFeeStatus + ", role=" + role + "]";
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }
}
