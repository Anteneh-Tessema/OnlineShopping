package com.cs490.onlineshopping.security;

public enum RolPermission {
    ORDER_READ("order:read"),
    ORDER_WRITE("order:write"),
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write");

    private final String permission;

    RolPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}