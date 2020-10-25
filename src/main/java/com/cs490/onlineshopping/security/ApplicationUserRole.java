package com.cs490.onlineshopping.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;


public enum ApplicationUserRole {
    STUDENT,
    ADMIN,
    ADMINTRAINEE;
}
