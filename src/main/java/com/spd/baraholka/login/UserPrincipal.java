package com.spd.baraholka.login;

import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.*;

public class UserPrincipal implements OAuth2User, UserDetails {

    private int id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    private String avatar;
    private String firstName;
    private String lastName;
    private String location;
    private String position;
    private String phoneNumber;
    private boolean isBlocked;
    private List<String> resourceLinks;
    private Set<Role> roles;

//    private transient UserService userService;

//    @Autowired
//    public UserPrincipal(UserService userService) {
//        this.userService = userService;
//    }

    public UserPrincipal(int id, String email, String password, Collection<? extends GrantedAuthority> authorities,
                         Map<String, Object> attributes) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public String getEmail() {
        return this.email;
    }

    public int getId() {
        return id;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserPrincipal(int id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
//        List<GrantedAuthority> authorities = Collections.
//                singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        UserPrincipal principal = new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), user.getAuthorities());
        principal.firstName = user.getFirstName();
        principal.lastName = user.getLastName();
        principal.avatar = user.getAvatar();
        principal.location = user.getLocation();
        principal.position = user.getPosition();
        principal.phoneNumber = user.getPhoneNumber();
        principal.roles = user.getRoles();
        return principal;

//        return new UserPrincipal(
//                user.getId(),
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
    }

    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }
}
