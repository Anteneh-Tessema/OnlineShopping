package com.cs490.onlineshopping.controller;

import com.cs490.onlineshopping.api.request.CategoryRequest;
import com.cs490.onlineshopping.api.request.ProductRequest;
import com.cs490.onlineshopping.dto.UserResponseDTO;

import org.modelmapper.ModelMapper;
import com.cs490.onlineshopping.model.Category;
import com.cs490.onlineshopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cs490.onlineshopping.model.Product;
import com.cs490.onlineshopping.model.Role;
import com.cs490.onlineshopping.model.User;
import com.cs490.onlineshopping.model.Vendor;
import com.cs490.onlineshopping.service.ProductService;
import com.cs490.onlineshopping.service.UserService;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;
	private CategoryService categoryService;

	@GetMapping("/vendors/{vendorid}")
	public ResponseEntity getAllProductsByVendor(@PathVariable("vendorid") Long vendor_id,
			@RequestParam Integer pageNumber) {
		try {

			Optional<User> vendor = Optional.of(userService.findById(vendor_id));
			if (vendor.isPresent()) {
				Vendor v = (Vendor) vendor.get();
				return new ResponseEntity<>(productService.findByVendor(v, pageNumber - 1), HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
	}

	// @Secured({"ROLE_CLIENT", "ROLE_VENDOR", "ROLE_ADMIN"})
	@GetMapping()
	public ResponseEntity<Page<Product>> getProducts(@RequestParam Integer pageNumber, @RequestParam String keyword) {
		try {
			return new ResponseEntity<>(productService.findAllByPage(pageNumber - 1, keyword), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{productid}")
	public ResponseEntity<Product> getProductById(@PathVariable("productid") Long productid) {
		try {
			Optional<Product> product = productService.findById(productid);
			if (product.isPresent()) {
				return new ResponseEntity<>(product.get(), HttpStatus.OK);
			}
			return new ResponseEntity<>(new Product(), HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			return new ResponseEntity<>(new Product(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured({ "ROLE_VENDOR" })
	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody ProductRequest productRequest) {
		try {
			Optional<User> vendor = Optional.of(userService.findById(productRequest.getVendor_id()));

			CategoryRequest categoryid = productRequest.getCategoryId();
			Optional<Category> category = categoryService.findById(categoryid.getId());
			if (!category.isPresent()) {
				throw new IllegalArgumentException("Category does not exist");
			}
			Product product = new Product();
			product.setCategory(category.get());

			if (vendor.isPresent()) {

				product.setCountInStock(productRequest.getCountInStock());
				product.setDescription(productRequest.getDescription());
				product.setImage(productRequest.getImage());
				product.setName(productRequest.getName());
				product.setPrice(productRequest.getPrice());
				product.setVendor((Vendor) vendor.get());

				productService.saveProduct(product);
				return new ResponseEntity<>(product, HttpStatus.OK);
			}
			return new ResponseEntity<>(new Product(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new Product(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Secured({ "ROLE_VENDOR" })
	@PostMapping()
	public ResponseEntity<Product> createProduct(HttpServletRequest req) {
		try {
			Optional<User> vendor = Optional.of(userService.whoami(req));
			if (vendor.isPresent()) {
				Product product = new Product();
				product.setCountInStock(0);
				product.setDescription("Description");
				product.setImage("Image Path");
				product.setName("Product Name");
				product.setPrice(0.00);
				product.setVendor((Vendor) vendor.get());
				product.setCategory(new Category());
				productService.saveProduct(product);
				return new ResponseEntity<>(product, HttpStatus.OK);
			}
			return new ResponseEntity<>(new Product(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new Product(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @author Amit Bhattarai
	 *
	 */
	@PutMapping()
	public ResponseEntity<Product> updateProductAdmin(@RequestBody ProductRequest productRequest,
			HttpServletRequest req) {
		try {
			Optional<User> user = Optional.of(userService.whoami(req));
			Optional<Product> productDb = productService.findById(productRequest.getId());
			if (productDb.isPresent()) {
				Product product = productDb.get();
				product.setCountInStock(productRequest.getCountInStock() != null ? productRequest.getCountInStock()
						: product.getCountInStock());
				product.setDescription(productRequest.getDescription() != null ? productRequest.getDescription()
						: product.getDescription());
				product.setImage(productRequest.getImage() != null ? productRequest.getImage() : product.getImage());
				product.setName(productRequest.getName() != null ? productRequest.getName() : product.getName());
				product.setPrice(productRequest.getPrice() != null ? productRequest.getPrice() : product.getPrice());
				if (user.get().getRole() == Role.ADMIN) {
					product.setActive(productRequest.getActive());
				}
				if (productRequest.getVendor_id() != null) {
					Optional<User> vendor = Optional.of(userService.findById(productRequest.getVendor_id()));
					if (vendor.isPresent()) {
						product.setVendor((Vendor) vendor.get());
					} else {
						return new ResponseEntity<>(new Product(), HttpStatus.BAD_REQUEST);
					}
				}

				CategoryRequest categoryid = productRequest.getCategoryId();
				Optional<Category> category = categoryService.findById(categoryid.getId());
				if (!category.isPresent()) {
					throw new IllegalArgumentException("Category does not exist");
				}

				product.setCategory(category.get());

				productService.saveProduct(product);
				return new ResponseEntity<>(product, HttpStatus.OK);
			}
			return new ResponseEntity<>(new Product(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new Product(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{productid}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable("productid") Long productid) {
		try {
			Optional<Product> productDb = productService.findById(productid);
			if (productDb.isPresent()) {

				productService.deleteProduct(productDb.get());
				return new ResponseEntity<>(true, HttpStatus.OK);
			}
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}