package org.bank.utils.util;

import io.jsonwebtoken.Jwts;
import org.bank.dto.CustomerDto;
import org.bank.dto.JwtResponseDto;
import org.bank.dto.Role;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class JwtUtilsTest {
    @Test
    public void testGenerateToken() {
        CustomerDto customer = CustomerDto.builder()
                .name("John Doe")
                .gender("Male")
                .phoneNumber("1234567890")
                .email("johndoe@example.com")
                .username("johndoe")
                .role(Role.CUSTOMER)
                .build();

        JwtResponseDto jwtResponse = JwtUtils.generateToken(customer);

        assertNotNull(jwtResponse);
        assertNotNull(jwtResponse.getToken());

        String token = jwtResponse.getToken();
        String name = Jwts.parser()
                .setSigningKey(JwtUtils.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("name", String.class);
        String gender = Jwts.parser()
                .setSigningKey(JwtUtils.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("gender", String.class);
        String phoneNumber = Jwts.parser()
                .setSigningKey(JwtUtils.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("phoneNumber", String.class);
        String email = Jwts.parser()
                .setSigningKey(JwtUtils.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);
        String username = Jwts.parser()
                .setSigningKey(JwtUtils.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("username", String.class);
        String role = Jwts.parser()
                .setSigningKey(JwtUtils.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
        Date expiration = Jwts.parser()
                .setSigningKey(JwtUtils.SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        assertEquals("John Doe", name);
        assertEquals("Male", gender);
        assertEquals("1234567890", phoneNumber);
        assertEquals("johndoe@example.com", email);
        assertEquals("johndoe", username);
        assertEquals("CUSTOMER", role);
        assertNotNull(expiration);
    }
}