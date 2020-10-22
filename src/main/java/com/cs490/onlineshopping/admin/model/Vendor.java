package com.cs490.onlineshopping.admin.model;

import javax.persistence.*;

@Entity
public class Vendor extends User {


    @OneToOne(cascade= {CascadeType.ALL})
    private Address address;

    private boolean registrationFeeStatus;

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

    public boolean getRegistrationFeeStatus() {
        return registrationFeeStatus;
    }

    public void setRegistrationFeeStatus(boolean registrationFeeStatus) {
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
        return "Vendor [vendorId=" + getId() + ", firstName=" + getFirstName() + ", lastName=" + getLastName() + ", userName="
                + getUserName() + ", password=" + getPassword() + ", address=" + address + ", registrationFeeStatus="
                + registrationFeeStatus + ", role=" + role + "]";
    }


}
