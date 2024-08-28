package org.bank.controller;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.bank.controller.base.BaseController;
import org.bank.dto.DefaultResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.bank.utils.util.JwtUtils.SECRET_KEY;

@RestController
@RequestMapping("/api/validate-token")
@RequiredArgsConstructor
public class TokenValidationController extends BaseController {

    @PostMapping()
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") final String authorization) {
        final String token = authorization.replace("Bearer ", "");
        return call(() -> Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody());
    }
}
