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

	public void setUpDefaultCardInfo() {
		cardService.registerCard("4242424242424242", "Amit", "Bhattarai", "12/23", "123", PaymentMethod.VISA,
				new BigDecimal("100000"));
		cardService.registerCard("5267599947265748", "Amit", "Bhattarai", "12/23", "532", PaymentMethod.MASTERCARD,
				new BigDecimal("100000"));
	}

	@Override
	public void run(String... params) throws Exception {
		User admin = new Admin();
		admin.setFirstname("System");
		admin.setLastname("Admin");
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setEmail("admin@email.com");
		admin.setRole(Role.ADMIN);

		userService.saveUserDemo(admin);

		User client = new Client();
		client.setFirstname("Amit");
		client.setLastname("Bhattarai");
		client.setUsername("client1");
		client.setPassword("client1");
		client.setEmail("client1@email.com");
		client.setRole(Role.CLIENT);

		userService.saveUserDemo(client);

		Vendor vendor = new Vendor();
		vendor.setFirstname("Amazon");
		vendor.setLastname("Inc");
		vendor.setUsername("amazon");
		vendor.setPassword("amazon");
		vendor.setEmail("amazon@email.com");
		vendor.setRole(Role.VENDOR);

		userService.saveUserDemo(vendor);

		Vendor vendor2 = new Vendor();
		vendor2.setFirstname("Ebay");
		vendor2.setLastname("Inc");
		vendor2.setUsername("ebay");
		vendor2.setPassword("ebay");
		vendor2.setEmail("ebay@email.com");
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

		Product[] products = new Product[9];
		products[0] = new Product(
				"New Apple Watch Series 6 (GPS, 44mm) - Space Gray Aluminum Case with Black Sport Band",
				"/images/apple_watch_series_6.jpg",
				"GPS model lets you take calls and reply to texts from your wrist\r\n"
						+ "Measure your blood oxygen with an all-new sensor and app\r\n"
						+ "Check your heart rhythm with the ECG app\r\n"
						,
				vendor, 429.00, 3, c2);
		products[1] = new Product("Apple MacBook Air (13-inch, 8GB RAM, 256GB SSD Storage)", "/images/macbook_air.jpg",
				"Stunning 13.3-inch Retina display with True Tone technology\r\n"
						+ "Backlit Magic Keyboard and Touch ID\r\n" + "Tenth-generation Intel Core i3 processor\r\n"
						,
				vendor2, 949.99, 12, c2);
		products[2] = new Product("New Apple iPad Air (10.9-inch, Wi-Fi, 64GB) - Rose Gold", "/images/ipad_air.jpg",
				"Bluetooth technology lets you connect it with compatible devices wirelessly "
						+ "High-quality AAC audio offers immersive listening experience Built-in "
						+ "microphone allows you to take calls while working",
				vendor, 599.00, 7, c3);
		products[3] = new Product("Apple Smart Keyboard iPad Pro 11-inch (2nd Generation)",
				"/images/smart_keyboard.jpg",
				"The Smart Keyboard Folio is designed to deliver a great typing experience on a full-size keyboard whenever you need it.\r\n"
						+ "No charging or pairing required. Its durable lightweight cover protects both the front and back of your 11-inch iPad Pro.\r\n"
						,
				vendor2, 169.99, 24, c4);
		products[4] = new Product("Apple iPhone 11 Pro, 64GB, Space Gray - Fully Unlocked", "/images/iphone.jpg",
				"Fully unlocked and compatible with any carrier of choice (e.g. AT&T, T-Mobile, Sprint, Verizon, US-Cellular, Cricket, Metro, etc.).",
				vendor, 799.00, 12, c2);
		products[5] = new Product("New Apple MacBook Pro (16-inch, 16GB RAM, 512GB Storage)", "/images/macbook_pro.jpg",
				"Ninth-generation 6-Core Intel Core i7 Processor\r\n"
						+ "Stunning 16-inch Retina Display with True Tone technology\r\n" + "Touch Bar and Touch ID\r\n"
						,
				vendor2, 2199.00, 3, c3);
		products[6] = new Product("New Apple MacBook Pro (16-inch, 16GB RAM, 512GB Storage)", "/images/macbook_pro.jpg",
				"2.7 GHz quad-core Intel Core i5 processor (Turbo Boost up to 3.2GHz) with 6MB L3 cache\r\n",
				vendor, 2199.00, 3, c3);
		products[7] = new Product("Apple iMac MD093LL/A - Intel Core I5-3330s - 21.5-Inch Display - 1TB HDD Desktop",
				"/images/imac.jpg",
				"Ninth-generation 6-Core Intel Core i7 Processor\r\n"
						+ "Stunning 16-inch Retina Display with True Tone technology\r\n" + "Touch Bar and Touch ID\r\n"
						+ "AMD Radeon Pro 5300M Graphics with GDDR6 memory",
				vendor, 524.00, 9, c3);
		products[8] = new Product("New Apple MacBook Pro (16-inch, 16GB RAM, 512GB Storage)", "/images/macbook_pro.jpg",
				"Ninth-generation 6-Core Intel Core i7 Processor\r\n"
						+ "Stunning 16-inch Retina Display with True Tone technology\r\n" + "Touch Bar and Touch ID\r\n"
						+ "AMD Radeon Pro 5300M Graphics with GDDR6 memory",
				vendor2, 2199.00, 3, c3);
		for (Product product : products) {
			productService.saveProduct(product);
		}

		setUpDefaultCardInfo();

	}
}