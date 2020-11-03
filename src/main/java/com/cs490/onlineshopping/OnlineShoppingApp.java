package com.cs490.onlineshopping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import com.cs490.onlineshopping.model.*;
import com.cs490.onlineshopping.service.CardService;
import com.cs490.onlineshopping.service.CategoryService;

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
  
  @Autowired
  CategoryService categoryService;

  public static void main(String[] args) {
    SpringApplication.run(OnlineShoppingApp.class, args);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  public void setUpDefaultCardInfo()
  {
      cardService.registerCard("4242424242424242","Amit","Bhattarai","12/23","123", PaymentMethod.VISA,new BigDecimal("100000"));
      cardService.registerCard("5267599947265748","Amit","Bhattarai","12/23","532", PaymentMethod.MASTERCARD,new BigDecimal("100000"));
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
    client.setRole(Role.CLIENT);

    userService.saveUserDemo(client);
    
    Vendor vendor = new Vendor();
    vendor.setFirstname("vendor");
    vendor.setLastname("lst");
    vendor.setUsername("vendor@email.com");
    vendor.setPassword("vendor");
    vendor.setEmail("vendor@email.com");
    vendor.setRole(Role.VENDOR);

    userService.saveUserDemo(vendor);
    
    Vendor vendor2 = new Vendor();
    vendor2.setFirstname("vendor2");
    vendor2.setLastname("lst2");
    vendor2.setUsername("vendor2@email.com");
    vendor2.setPassword("vendor2");
    vendor2.setEmail("vendor2@email.com");
    vendor2.setRole(Role.VENDOR);

    userService.saveUserDemo(vendor2);
    
    Category c1 = new Category("General");
    Category c2 = new Category("Electronics");
    
    Category c3 = new Category("Home");
    Category c4 = new Category("Kitchen");
    
    categoryService.saveCategory(c1);
    categoryService.saveCategory(c2);
    categoryService.saveCategory(c3);
    categoryService.saveCategory(c4);
    
    Product[] products = new Product[6];
    products[0]= new Product("Airpods Wireless Bluetooth Headphones","/images/airpods.jpg",
                        "Bluetooth technology lets you connect it with compatible devices wirelessly "
                        		+ "High-quality AAC audio offers immersive listening experience Built-in "
                        		+ "microphone allows you to take calls while working", vendor, 89.99, 3, c1);
    products[1]= new Product("Airpods Wireless Bluetooth Headphones","/images/airpods.jpg",
            "Bluetooth technology lets you connect it with compatible devices wirelessly "
            		+ "High-quality AAC audio offers immersive listening experience Built-in "
            		+ "microphone allows you to take calls while working", vendor2, 89.99, 3, c2);
    products[2]= new Product("Airpods Wireless Bluetooth Headphones","/images/airpods.jpg",
            "Bluetooth technology lets you connect it with compatible devices wirelessly "
            		+ "High-quality AAC audio offers immersive listening experience Built-in "
            		+ "microphone allows you to take calls while working", vendor, 89.99, 3, c3);
    products[3]= new Product("Airpods Wireless Bluetooth Headphones","/images/airpods.jpg",
            "Bluetooth technology lets you connect it with compatible devices wirelessly "
            		+ "High-quality AAC audio offers immersive listening experience Built-in "
            		+ "microphone allows you to take calls while working", vendor2, 89.99, 3, c4);
    products[4]= new Product("Airpods Wireless Bluetooth Headphones","/images/airpods.jpg",
            "Bluetooth technology lets you connect it with compatible devices wirelessly "
            		+ "High-quality AAC audio offers immersive listening experience Built-in "
            		+ "microphone allows you to take calls while working", vendor, 89.99, 3, c2);
    products[5]= new Product("Airpods Wireless Bluetooth Headphones","/images/airpods.jpg",
            "Bluetooth technology lets you connect it with compatible devices wirelessly "
            		+ "High-quality AAC audio offers immersive listening experience Built-in "
            		+ "microphone allows you to take calls while working", vendor2, 89.99, 3, c3);
    for(Product product: products) {
    	productService.saveProduct(product);
    }

    setUpDefaultCardInfo();

}
}