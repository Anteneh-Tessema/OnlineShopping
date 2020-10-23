package com.cs490.onlineshopping.admin.model;

import javax.persistence.*;

@Entity
public class Guest extends User{

    @Enumerated(EnumType.STRING)
    private Role role=Role.GUEST;

    @OneToOne(cascade= {CascadeType.ALL})
    private Address address;

    public Guest() {
        super();
    }

    public Guest(String firstName, String lastName, String username, String password, Address address) {
        super();
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "EndUser [userId=" + super.toString() + ", role=" + role + ", address=" + address + "]";
    }


}
