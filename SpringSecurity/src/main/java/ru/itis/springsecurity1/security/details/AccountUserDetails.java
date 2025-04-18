package ru.itis.springsecurity1.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.springsecurity1.model.AccountEntity;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class AccountUserDetails implements UserDetails {

    private final AccountEntity account;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = account.getRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !account.getState().equals(AccountEntity.State.BANNED);
    }

    @Override
    public boolean isEnabled() {
        return account.getState().equals(AccountEntity.State.OK);
    }
}
