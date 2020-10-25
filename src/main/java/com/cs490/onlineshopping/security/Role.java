package com.cs490.onlineshopping.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Sets.newHashSet(RolPermission.ORDER_READ, RolPermission.ORDER_WRITE)),
    CLIENT(Sets.newHashSet(RolPermission.ORDER_READ, RolPermission.ORDER_WRITE)),
    VENDOR(Sets.newHashSet(RolPermission.ORDER_READ, RolPermission.ORDER_WRITE)),
    GUEST(Sets.newHashSet(RolPermission.ORDER_READ, RolPermission.ORDER_WRITE));

    private final Set<RolPermission> permissions;

    Role(Set<RolPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<RolPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
