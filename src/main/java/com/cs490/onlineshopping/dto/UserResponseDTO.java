package com.cs490.onlineshopping.dto;

import java.util.List;

import com.cs490.onlineshopping.model.Role;

import io.swagger.annotations.ApiModelProperty;

public class UserResponseDTO {

  @ApiModelProperty(position = 0)
  private Integer id;
  @ApiModelProperty(position = 1)
  private String username;
  @ApiModelProperty(position = 2)
  private String email;
  @ApiModelProperty(position = 3)
  Role role;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Role getRoles() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

}
