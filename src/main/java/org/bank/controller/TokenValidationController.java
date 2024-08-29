package org.bank.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.bank.controller.base.BaseController;
import org.bank.service.TokenValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/validate-token")
@RequiredArgsConstructor
public class TokenValidationController extends BaseController {
    private final TokenValidationService service;

    @PostMapping()
    public ResponseEntity<?> validateToken(@NotBlank @RequestHeader("Authorization") final String authorization) {
        return call(() -> service.validateToken(authorization));
    }
}
