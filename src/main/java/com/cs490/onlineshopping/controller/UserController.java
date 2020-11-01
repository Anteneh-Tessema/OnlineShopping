package com.cs490.onlineshopping.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs490.onlineshopping.dto.UserDataDTO;
import com.cs490.onlineshopping.dto.UserResponseDTO;
import com.cs490.onlineshopping.model.Admin;
import com.cs490.onlineshopping.model.Client;
import com.cs490.onlineshopping.model.Product;
import com.cs490.onlineshopping.model.Role;
import com.cs490.onlineshopping.model.User;
import com.cs490.onlineshopping.model.Vendor;
import com.cs490.onlineshopping.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/api/users")
@Api(tags = "users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private ModelMapper modelMapper;
  
  @Secured("ROLE_ADMIN")
  @GetMapping()
  @ApiOperation(value = "${UserController.getUsers}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 422, message = "Invalid username/password supplied")})
  public ResponseEntity<List<User>> getUsers() {
	  try {      	
          return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
      }
      catch (Exception ex) {
          return new ResponseEntity<>(new ArrayList<>() , HttpStatus.INTERNAL_SERVER_ERROR) ;
      }
  }

  @PostMapping("/login")
  @ApiOperation(value = "${UserController.signin}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 422, message = "Invalid username/password supplied")})
  public String login(//
      @ApiParam("Username") @RequestParam String username, //
      @ApiParam("Password") @RequestParam String password) {
    return userService.signin(username, password);
  }

  @PostMapping("/register")
  @ApiOperation(value = "${UserController.signup}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 422, message = "Username is already in use")})
  public String signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
	  if(user.getRole().getAuthority() == "VENDOR") {
		  return userService.signup(modelMapper.map(user, Vendor.class));
	  }
	  else if(user.getRole().getAuthority() == "CLIENT") {
			  return userService.signup(modelMapper.map(user, Client.class));		  
	  }
	  else {
			  return userService.signup(modelMapper.map(user, Admin.class));		  
	  }
    
  }
  
  @GetMapping(value = "/{userId}")  
  @ApiOperation(value = "${UserController.search}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public User getUser(@PathVariable Integer userId) {
    return userService.findById(userId);
  }

  @DeleteMapping(value = "/{userId}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "${UserController.delete}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public Integer delete(@ApiParam("Username") @PathVariable Integer userId) {
    userService.delete(userId);
    return userId;
  }

  
  @PutMapping("/profile")  
  @ApiOperation(value = "${UserController.put}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<User> updateUser(@RequestBody User user) {	
	  System.out.println("USER" + user.getId());
	  try {      	         
                           
      	return new ResponseEntity<>(userService.saveUser(user),HttpStatus.OK);
          
      }
      catch (Exception e){
          return new ResponseEntity<>(new User(),HttpStatus.INTERNAL_SERVER_ERROR);
      }
    
  }
  @GetMapping(value = "username/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class)
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
    return modelMapper.map(userService.search(username), UserResponseDTO.class);
  }

  @GetMapping(value = "/me")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT') or hasRole('ROLE_VENDOR')")
  @ApiOperation(value = "${UserController.me}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public UserResponseDTO whoami(HttpServletRequest req) {
    return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
  }

  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }

}
