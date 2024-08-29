package org.bank.utils.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bank.dto.CustomerCredentialDto;
import org.bank.dto.CustomerDto;
import org.bank.dto.JwtResponseDto;
import org.bank.service.implmentation.JwtServiceImpl;

import java.util.Base64;
import java.util.Date;

public class JwtUtils {
    private static final Logger logger = LogManager.getLogger(JwtServiceImpl.class);
    public static final byte[] SECRET_KEY = Base64.getDecoder().decode("iJ+G1WqnA+qiR6Myd8vHZvvDjvymtsfNK52f1AtJAH7CCg8rFkU2W2N2Wpx5pti7oqF2zjY8TJ4DgU+TCg5VfQ==");
    private static final long EXPIRATION_TIME = 300_000;

    public static JwtResponseDto generateToken(CustomerDto customer) {
        logger.info("Generating JWT For {}", customer.getUsername());
        return JwtResponseDto
                .builder()
                .token(Jwts.builder()
                        .claim("name", customer.getName())
                        .claim("gender", customer.getGender())
                        .claim("phoneNumber", customer.getPhoneNumber())
                        .claim("email", customer.getEmail())
                        .claim("username", customer.getUsername())
                        .claim("role", customer.getRole())
                        .setIssuedAt(new Date())
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                        .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                        .compact())
                .build();
    }
}
