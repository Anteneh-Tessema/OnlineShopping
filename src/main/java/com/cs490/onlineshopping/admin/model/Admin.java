package com.cs490.onlineshopping.admin.model;

import com.cs490.onlineshopping.security.Role;

import javax.persistence.*;

@Entity
public class Admin extends User{

    @Enumerated(EnumType.STRING)
    private Role role = Role.ADMIN;

    public Admin() {
        super();
    }

    public Admin(String firstName, String lastName, String username, String password, Role role) {
        super();
        super.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.role = role;
    }


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Client [clientId=" + super.toString()+ ", role=" + role + "]";
    }


}