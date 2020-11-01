package com.cs490.onlineshopping.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cs490.onlineshopping.exception.CustomException;
import com.cs490.onlineshopping.model.User;
import com.cs490.onlineshopping.repository.UserRepository;
import com.cs490.onlineshopping.security.JwtTokenProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public String signin(String username, String password) {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      String token =  jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRole());
      
      ObjectMapper objectMapper = new ObjectMapper();
      String userInfo = objectMapper.writeValueAsString(userRepository.findByUsername(username));
      ObjectNode json = (ObjectNode) objectMapper.readTree( userInfo);
      json.put("token", token);
      return json.toString();
      
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    } catch (JsonProcessingException e) {		
    	throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
	}
  }

  public String signup(User user) {
	  if (!userRepository.existsByUsername(user.getUsername())) {
		  try {
	      user.setPassword(passwordEncoder.encode(user.getPassword()));
	      User nuser = userRepository.save(user);
	      String token = jwtTokenProvider.createToken(nuser.getUsername(), nuser.getRole());
	      ObjectMapper objectMapper = new ObjectMapper();
	      String userInfo = objectMapper.writeValueAsString(userRepository.findByUsername(nuser.getUsername()));
	      
	      ObjectNode json = (ObjectNode) objectMapper.readTree( userInfo);
	      json.put("token", token);
	      System.out.println(json.toString());
	      return json.toString();
		  }
		  catch (JsonProcessingException e) {		
		    	throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
			}
	    } 
	  
	  else {
	      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
	    }
  }
  
  public List<User> findAllUsers(){
	  return userRepository.findAll();
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }
  
  public void delete(Integer userId) {
	  userRepository.deleteById(userId);
  }

  public User search(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }
  
  public User saveUserDemo(User user) {
	  if (!userRepository.existsByUsername(user.getUsername())) {
		  user.setPassword(passwordEncoder.encode(user.getPassword()));
	      return userRepository.save(user);
	  }
	  
	  throw new CustomException("The user already exists", HttpStatus.NOT_FOUND);
  }
  
  public User saveUser(User user) {	  
	  if (userRepository.existsById(user.getId())) {	  
	      User u = userRepository.findById(user.getId()).get();
	      
	      u.setFirstname(user.getFirstname());
		  u.setLastname(user.getLastname());
		  u.setEmail(user.getEmail());
		  u.setPassword(passwordEncoder.encode(user.getPassword()));
		  u.setUsername(user.getUsername()); 
		  return userRepository.save(u);	
	      
		    
	    } 
	  
	  else {
	      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
	    }
	  
  }
  
  public User findById(Integer id) {
	    Optional<User> user = userRepository.findById(id);
	    if (user.isPresent()) {
	    	return user.get();
	    }
	    
	    throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
	    
	  }

  public User whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public String refresh(String username) {
    return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRole());
  }

}
