package com.formbasedsecindb.security.beans;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserDetailsImplementation implements UserDetails {
    private Integer systemUserId;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private String status;

    public UserDetailsImplementation(Integer systemUserId,String username, String password, String role, String status) {
        this.username = username;
        this.password = password;
        this.authorities = Arrays.asList(new GrantedAuthority[]{new SimpleGrantedAuthority(role)});
        this.status = status;
        this.systemUserId = systemUserId;
    }

    public Integer getSystemUserId() {
        return systemUserId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !status.equals("E");
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.status.equals("L");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status.equals("A");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
