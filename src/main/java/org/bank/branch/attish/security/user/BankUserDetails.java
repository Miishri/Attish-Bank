package org.bank.branch.attish.security.user;

import org.bank.branch.attish.models.BankUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class BankUserDetails implements UserDetails {
    private final String username;
    private final String password;
    private final GrantedAuthority authorities;

    public BankUserDetails(BankUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new SimpleGrantedAuthority("*");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
