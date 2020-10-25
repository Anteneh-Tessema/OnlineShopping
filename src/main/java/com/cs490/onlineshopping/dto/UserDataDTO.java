package com.cs490.onlineshopping.dto;

import java.util.List;

import com.cs490.onlineshopping.model.Role;

import io.swagger.annotations.ApiModelProperty;

public class UserDataDTO {
  
	@ApiModelProperty(position = 0)
	  private String firstname;
	@ApiModelProperty(position = 1)
	  private String lastname;
  @ApiModelProperty(position = 2)
  private String username;
  @ApiModelProperty(position = 3)
  private String email;
  @ApiModelProperty(position = 4)
  private String password;
  @ApiModelProperty(position = 5)
  Role role;

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

public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

public void setRole(Role role) {
	this.role = role;
}

}
