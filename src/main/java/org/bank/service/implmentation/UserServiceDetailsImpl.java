package org.bank.service.implmentation;

import lombok.RequiredArgsConstructor;
import org.bank.dto.CustomerCredentialDto;
import org.bank.dto.security.User;
import org.bank.service.CustomerCredentialService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceDetailsImpl implements UserDetailsService {
    private final CustomerCredentialService service;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return toUserDetails(service.findCustomerCredentialByUsername(username));
    }

    private UserDetails toUserDetails(final CustomerCredentialDto credential) {
        return User
                .builder()
                .password(credential.getPassword())
                .username(credential.getUsername())
                .role(credential.getRole())
                .build();
    }
}
