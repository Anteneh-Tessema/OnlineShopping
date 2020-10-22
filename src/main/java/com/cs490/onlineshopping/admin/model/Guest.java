package com.cs490.onlineshopping.admin.model;

import javax.persistence.*;

@Entity
public class Guest extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Role role=Role.GUEST;

    @OneToOne(cascade= {CascadeType.ALL})
    private Address address;

    public Guest() {
        super();
    }

    public Guest(String firstName, String lastName, String userName, String password, Address address) {
        super();
        super.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUserName(userName);
        this.setPassword(password);
        this.address = address;
    }

    public int getId() {
        return id;
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
        return "EndUser [userId=" + id + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", userName="
                + getUserName() + ", password=" + getPassword() + ", role=" + role + ", address=" + address + "]";
    }


}
