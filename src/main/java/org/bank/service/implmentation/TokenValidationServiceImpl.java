package org.bank.service.implmentation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.bank.dto.DefaultResponseDto;
import org.bank.service.TokenValidationService;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.bank.utils.util.JwtUtils.SECRET_KEY;

@Service
public class TokenValidationServiceImpl
        implements TokenValidationService {


    @Override
    public Claims validateToken(String authorizationHeader) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(extractTokenFromAuthorizationHeader(authorizationHeader))
                .getBody();
    }

    private String extractTokenFromAuthorizationHeader(final String authorizationHeader) {
        return authorizationHeader.replace("Bearer ", "");
    }
}
