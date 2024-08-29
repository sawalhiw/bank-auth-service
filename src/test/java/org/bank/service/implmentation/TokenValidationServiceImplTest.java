package org.bank.service.implmentation;

import io.jsonwebtoken.Claims;
import org.bank.service.TokenValidationService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TokenValidationServiceImplTest {
    private final TokenValidationService service = new TokenValidationServiceImpl();

    @Test
    public void testValidateToken() {
        final String token = "eyJhbGciOiJIUzUxMiJ9.eyJuYW1lIjoiYWRtaW4iLCJnZW5k" +
                "ZXIiOiJtYWxlIiwicGhvbmVOdW1iZXIiOiIrOTYyNzk2MDU4ODgzIiwiZW1haWwi" +
                "OiJhZG1pbkBnbWFpbC5jb20iLCJ1c2VybmFtZSI6ImFkbWluIiwicm9sZSI6IkFE" +
                "TUlOIiwiaWF0IjoxNzI0OTExMzA4LCJleHAiOjMwMDAwMTcyNDkxMTMwOH0.AxGB" +
                "qCmKiRqUcHWqvT5BkrPW0h82414uIGGYJCphwT1CP7GgSnGrq0fElMp6PXhAaJVN" +
                "E0hbs_SOu2oZv3nh2Q";

        Claims claims = service.validateToken(token);

        assertNotNull(claims);
        assertNotNull(claims.get("role"));
        assertEquals(claims.get("role"), "ADMIN");
    }
}