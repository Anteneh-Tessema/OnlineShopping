package com.cs490.onlineshopping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String address;
    private String state;
    private String city;
    private String postalCode;
    private String country;

    public Address() {
        super();
    }

    public Address(String firstname, String lastname, String address, String state, String city, String postalCode,
			String country) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.state = state;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	@Override
    public String toString() {
        return "Address [addressId=" + id + ", state=" + state + ", city=" + city + ", postalCode=" + postalCode;
    }



}
