package com.cs490.onlineshopping.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserResponse {
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private int id;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String firstName;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String lastName;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String username;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String password;
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private String cause;

    public UserResponse(int id, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public UserResponse(String cause) {
        this.cause = cause;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }
}
