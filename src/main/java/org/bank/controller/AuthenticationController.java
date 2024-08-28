package org.bank.controller;

import lombok.RequiredArgsConstructor;
import org.bank.controller.base.BaseController;
import org.bank.dto.JwtRequestDto;
import org.bank.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/login")
public class AuthenticationController extends BaseController {
    private final AuthenticationService service;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody final JwtRequestDto jwtRequestDto) {
        return call(() -> service.login(jwtRequestDto));
    }

}
