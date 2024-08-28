package org.bank.controller;

import lombok.RequiredArgsConstructor;
import org.bank.controller.base.BaseController;
import org.bank.dto.DefaultResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/validate-token")
@RequiredArgsConstructor
public class TokenValidationController extends BaseController {

    @PostMapping()
    public ResponseEntity<?> validateToken() {
        return call(() -> DefaultResponseDto
                .builder()
                .response("Token is valid")
                .build());
    }
}
