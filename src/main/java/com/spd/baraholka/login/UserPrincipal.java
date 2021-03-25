package com.spd.baraholka.login;

import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.persistance.entities.UserAdditionalResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserPrincipal implements OAuth2User, UserDetails {

    private final int id;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    private String avatar;
    private String firstName;
    private String lastName;
    private String location;
    private String position;
    private String phoneNumber;
    private boolean isBlocked;
    private List<UserAdditionalResource> resourceLinks;
    private Set<Role> roles;

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

    public String getAvatar() {
        return avatar;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLocation() {
        return location;
    }

    public String getPosition() {
        return position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public List<UserAdditionalResource> getResourceLinks() {
        return resourceLinks;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void setResourceLinks(List<UserAdditionalResource> resourceLinks) {
        this.resourceLinks = resourceLinks;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
        UserPrincipal principal = new UserPrincipal(user.getId(), user.getEmail(), user.getPassword(), user.getAuthorities());
        principal.setFirstName(user.getFirstName());
        principal.setLastName(user.getLastName());
        principal.setAvatar(user.getImageUrl());
        principal.setLocation(user.getLocation());
        principal.setPosition(user.getPosition());
        principal.setPhoneNumber(user.getPhoneNumber());
        principal.setRoles(user.getRoles());
        principal.setResourceLinks(user.getResourceLinks());
        principal.setBlocked(user.isBlocked());
        return principal;
    }

    public static UserPrincipal create(User user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }
}
