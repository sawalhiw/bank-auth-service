package org.bank.service;

import org.bank.dto.JwtRequestDto;
import org.bank.dto.JwtResponseDto;

import java.security.Principal;

public interface AuthenticationService {
    JwtResponseDto login(final JwtRequestDto jwtRequestDto);
}
