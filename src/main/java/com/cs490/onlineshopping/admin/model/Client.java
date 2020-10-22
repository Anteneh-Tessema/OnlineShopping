package com.cs490.onlineshopping.admin.model;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Role role;

    @OneToOne(cascade= {CascadeType.ALL})
    private Address address;

    public Client() {
        super();
    }

    public Client(String firstName, String lastName, String userName, String password, Role role, Address address) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.address = address;
    }

    public int getClientId() {
        return clientId;
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
        return "Client [clientId=" + clientId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
                + userName + ", password=" + password + ", role=" + role + ", address=" + address + "]";
    }


}



