package com.cs490.onlineshopping.model;
import javax.persistence.*;

@Entity
public class Admin extends User{

    public Admin() {
        super();
    }



    @Override
    public String toString() {
        return "Client [clientId=" + super.toString()+ ", role=" + this.getRole() + "]";
    }


}