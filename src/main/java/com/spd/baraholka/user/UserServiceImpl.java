package com.spd.baraholka.user;

import com.spd.baraholka.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("UserDetailsServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final String USER_NOT_FOUND = "User not found";

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(User user) {
        repository.create(user);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public int count() {
        return repository.count();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username);
        Set<Role> authorities = findRolesByUserId(user.getId());
        user.setRoles(authorities);
        return user;
    }

    @Override
    public Set<Role> findRolesByUserId(int id) {
        return repository.findRolesByUserId(id);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> optionalUser = repository.findByEmail(email);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
        Set<Role> roles = findRolesByUserId(user.getId());
        user.setRoles(roles);
        return user;
    }

//    private User buildUserForAuthentication(foo.bar.User user,
//                                            List<GrantedAuthority> authorities) {
//        String username = user.getUsername();
//        String password = user.getPassword();
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//
//        return new CurrentUser(username, password, enabled, accountNonExpired, credentialsNonExpired,
//                accountNonLocked, authorities);
//    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {

        Set<GrantedAuthority> authorities = new HashSet<>();

        // Build user's authorities
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }

        return new ArrayList<GrantedAuthority>(authorities);
    }
}
