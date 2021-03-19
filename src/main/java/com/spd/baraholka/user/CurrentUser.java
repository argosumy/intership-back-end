//package com.spd.baraholka.user;
//
//import com.spd.baraholka.role.Role;
//import com.spd.baraholka.role.UserAuthority;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
//public class CurrentUser extends org.springframework.security.core.userdetails.User implements UserDetails {
//
//    @Override
//    public String getPassword() {
//        return "N/A";
//    }
//
//    @Override
//    public String getUsername() {
//        return this.email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return !isBlocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
