package org.bank.service;

import io.jsonwebtoken.Claims;

public interface TokenValidationService {
    Claims validateToken(String authorizationHeader);
}
