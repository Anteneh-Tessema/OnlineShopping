package com.cs490.onlineshopping.admin.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
public class Client extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade= {CascadeType.ALL})
    private Address address;

    public Client() {
        super();
    }

    public Client(String firstName, String lastName, String userName, String password, Role role, Address address) {
        super();
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUserName(userName);
        this.setPassword(password);
        this.role = role;
        this.address = address;
    }

    public int getId() {
        return id;
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
        return "Client [clientId=" + id + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", userName="
                + getUserName() + ", password=" + getPassword() + ", role=" + role + ", address=" + address + "]";
    }


}



