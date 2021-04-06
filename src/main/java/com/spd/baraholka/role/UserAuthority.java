package com.spd.baraholka.role;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority {

    private final Role role;

    public UserAuthority(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return this.role;
    }

    @Override
    public String getAuthority() {
        return this.role.name();
    }
}
