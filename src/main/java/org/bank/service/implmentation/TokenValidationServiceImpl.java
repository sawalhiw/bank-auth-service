package org.bank.service.implmentation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bank.service.TokenValidationService;
import org.springframework.stereotype.Service;


import static org.bank.utils.util.JwtUtils.SECRET_KEY;

@Service
public class TokenValidationServiceImpl implements TokenValidationService {
    private static final Logger logger = LogManager.getLogger(TokenValidationServiceImpl.class);


    @Override
    public Claims validateToken(String authorizationHeader) {
        logger.info("Validating JWT Token.");
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
