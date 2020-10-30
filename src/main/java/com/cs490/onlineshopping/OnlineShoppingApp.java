package com.cs490.onlineshopping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import com.cs490.onlineshopping.model.*;
import com.cs490.onlineshopping.service.CardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cs490.onlineshopping.service.ProductService;
import com.cs490.onlineshopping.service.UserService;

@SpringBootApplication
public class OnlineShoppingApp implements CommandLineRunner {

  @Autowired
  UserService userService;
  
  @Autowired
  ProductService productService;

  @Autowired
  CardService cardService;

  public static void main(String[] args) {
    SpringApplication.run(OnlineShoppingApp.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  public void setUpDefaultCardInfo()
  {
      cardService.registerCard("1111222233334444","Muyinza","Daniel","12/24","531", PaymentMethod.VISA,new BigDecimal("100000"));
      cardService.registerCard("1111222233334443","Muyinza","Daniel","12/24","532", PaymentMethod.VISA,new BigDecimal("100000"));
      cardService.registerCard("1111222233334442","Muyinza","Daniel","12/24","533", PaymentMethod.VISA,new BigDecimal("100000"));
      cardService.registerCard("1111222233334441","Muyinza","Daniel","12/24","534", PaymentMethod.VISA,new BigDecimal("100000"));
      cardService.registerCard("1111222233334445","Muyinza","Daniel","12/24","535", PaymentMethod.VISA,new BigDecimal("100000"));
      cardService.registerCard("1111222233334446","Muyinza","Daniel","12/24","536", PaymentMethod.VISA,new BigDecimal("100000"));
  }

  @Override
  public void run(String... params) throws Exception {
    User admin = new Admin();
    admin.setFirstname("Admin");
    admin.setLastname("lst");
    admin.setUsername("admin@email.com");
    admin.setPassword("admin");
    admin.setEmail("admin@email.com");
    admin.setRole(Role.ADMIN);

    userService.saveUserDemo(admin);

    User client = new Client();
    client.setFirstname("client");
    client.setLastname("lst");
    client.setUsername("client@email.com");
    client.setPassword("client");
    client.setEmail("client@email.com");
    client.setRole(Role.SHOPPER);

    userService.saveUserDemo(client);
    
    Vendor vendor = new Vendor();
    vendor.setFirstname("vendor");
    vendor.setLastname("lst");
    vendor.setUsername("vendor@email.com");
    vendor.setPassword("vendor");
    vendor.setEmail("vendor@email.com");
    vendor.setRole(Role.VENDOR);

    userService.saveUserDemo(vendor);
    
    Product[] products = new Product[6];
    products[0]= new Product("Airpods Wireless Bluetooth Headphones","/images/airpods.jpg",
                        "Bluetooth technology lets you connect it with compatible devices wirelessly "
                        		+ "High-quality AAC audio offers immersive listening experience Built-in "
                        		+ "microphone allows you to take calls while working", vendor, 89.99, 3);
    products[1]= new Product("Airpods Wireless Bluetooth Headphones","/images/airpods.jpg",
            "Bluetooth technology lets you connect it with compatible devices wirelessly "
            		+ "High-quality AAC audio offers immersive listening experience Built-in "
            		+ "microphone allows you to take calls while working", vendor, 89.99, 3);
    products[2]= new Product("Airpods Wireless Bluetooth Headphones","/images/airpods.jpg",
            "Bluetooth technology lets you connect it with compatible devices wirelessly "
            		+ "High-quality AAC audio offers immersive listening experience Built-in "
            		+ "microphone allows you to take calls while working", vendor, 89.99, 3);
    products[3]= new Product("Airpods Wireless Bluetooth Headphones","/images/airpods.jpg",
            "Bluetooth technology lets you connect it with compatible devices wirelessly "
            		+ "High-quality AAC audio offers immersive listening experience Built-in "
            		+ "microphone allows you to take calls while working", vendor, 89.99, 3);
    products[4]= new Product("Airpods Wireless Bluetooth Headphones","/images/airpods.jpg",
            "Bluetooth technology lets you connect it with compatible devices wirelessly "
            		+ "High-quality AAC audio offers immersive listening experience Built-in "
            		+ "microphone allows you to take calls while working", vendor, 89.99, 3);
    products[5]= new Product("Airpods Wireless Bluetooth Headphones","/images/airpods.jpg",
            "Bluetooth technology lets you connect it with compatible devices wirelessly "
            		+ "High-quality AAC audio offers immersive listening experience Built-in "
            		+ "microphone allows you to take calls while working", vendor, 89.99, 3);
    for(Product product: products) {
    	productService.saveProduct(product);
    }

    setUpDefaultCardInfo();

}
}