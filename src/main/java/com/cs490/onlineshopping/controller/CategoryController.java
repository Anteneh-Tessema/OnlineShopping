package com.cs490.onlineshopping.controller;

import com.cs490.onlineshopping.api.request.ProductRequest;
import com.cs490.onlineshopping.dto.UserDataDTO;
import com.cs490.onlineshopping.dto.UserResponseDTO;
import com.cs490.onlineshopping.model.Category;
import com.cs490.onlineshopping.model.Product;
import com.cs490.onlineshopping.model.User;
import com.cs490.onlineshopping.model.Vendor;
import com.cs490.onlineshopping.service.CategoryService;
import com.cs490.onlineshopping.service.UserService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping()
  public ResponseEntity<List<Category>> getProducts(){
    try {
      return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    catch (Exception ex) {
      return new ResponseEntity<>(new ArrayList<>() , HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{categoryid}")
  public ResponseEntity<Category> getCategoryById(@PathVariable("categoryid") Integer categoryid){
    try{
      Optional<Category> category = categoryService.findById(categoryid);
      if(category.isPresent()){
        return new ResponseEntity<>(category.get(), HttpStatus.OK);
      }
      return new ResponseEntity<>(new Category(), HttpStatus.BAD_REQUEST);
    }
    catch (Exception ex) {
      return new ResponseEntity<>(new Category() , HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("/save")
  public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
    try {
      if(category.getName() != null) {
        return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.OK);
      } else  {
        return new ResponseEntity<>(new Category(),HttpStatus.BAD_REQUEST);
      }
    }
    catch (Exception e){
      return new ResponseEntity<>(new Category(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/update")
  public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
    try {
      Optional<Category> ca = categoryService.findById(category.getId());
      if(ca.isPresent()){
        Category categoryDb = ca.get();
        categoryDb.setName(category.getName()!= null ? category.getName() : categoryDb.getName());
        return new ResponseEntity<>(categoryService.saveCategory(categoryDb), HttpStatus.OK);
      }
      return new ResponseEntity<>(new Category(),HttpStatus.BAD_REQUEST);
    }
    catch (Exception e){
      return new ResponseEntity<>(new Category(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/delete/{categoryid}")
  public ResponseEntity<Boolean> deleteCategory(@PathVariable("categoryid") Integer categoryid) {
    try {
      Optional<Category> categoryDb = categoryService.findById(categoryid);
      if(categoryDb.isPresent()){

        categoryService.deleteCategory(categoryDb.get());
        return new ResponseEntity<>(true,HttpStatus.OK);
      }
      return new ResponseEntity<>(false,HttpStatus.BAD_REQUEST);
    }
    catch (Exception e){
      return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
