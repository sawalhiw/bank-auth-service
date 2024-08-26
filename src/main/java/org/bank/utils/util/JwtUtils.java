package org.bank.utils.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bank.dto.CustomerCredentialDto;
import org.bank.dto.CustomerDto;

import java.util.Base64;
import java.util.Date;

public class JwtUtils {
    public static final byte[] SECRET_KEY = Base64.getDecoder().decode("iJ+G1WqnA+qiR6Myd8vHZvvDjvymtsfNK52f1AtJAH7CCg8rFkU2W2N2Wpx5pti7oqF2zjY8TJ4DgU+TCg5VfQ==");
    private static final long EXPIRATION_TIME = 300_000;

    public static String generateToken(CustomerDto customer) {
        return Jwts.builder()
                .claim("name", customer.getName())
                .claim("gender", customer.getGender())
                .claim("phoneNumber", customer.getPhoneNumber())
                .claim("email", customer.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
