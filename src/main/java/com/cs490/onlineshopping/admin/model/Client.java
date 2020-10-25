package com.cs490.onlineshopping.admin.model;

import java.util.List;

import javax.persistence.*;

import com.cs490.onlineshopping.order.model.Order;

@Entity
public class Client extends User{

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade= {CascadeType.ALL})
    private Address address;

    public Client(String firstName, String lastName, String username, String password,Address address) {
        super(firstName, lastName, username, password);
        this.role = Role.CLIENT;
    }

    public Client() {
        super();
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

	public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client [clientId=" + super.toString()+ ", role=" + role + ", address=" + address + "]";
    }


}