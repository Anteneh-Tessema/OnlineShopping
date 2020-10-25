package com.cs490.onlineshopping.model;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
public class Client extends User{   

     public Client() {
        super();
    } 

   
    @Override
    public String toString() {
        return "Client [clientId=" + super.toString()+ ", role=" + this.getRole() + "]";
    }


}