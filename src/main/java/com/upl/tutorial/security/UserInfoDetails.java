package com.upl.tutorial.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.upl.tutorial.model.Users;

public class UserInfoDetails implements UserDetails {
    private final String userName;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public UserInfoDetails(Users userInfo) {
        this.userName = userInfo.getEmail(); // Use email as username
        this.password = userInfo.getPassword();
        String roleName = "ROLE_" + userInfo.getRole().name();
        System.out.println("****  roleName *** :"+roleName);
        this.authorities = List.of(roleName)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;

    }

}
