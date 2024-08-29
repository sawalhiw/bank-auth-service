package org.bank.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bank.controller.base.BaseController;
import org.bank.dto.CustomerCredentialDto;
import org.bank.service.CustomerCredentialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/credentials")
@RequiredArgsConstructor
public class CustomerCredentialController extends BaseController {
    private final CustomerCredentialService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CustomerCredentialDto dto) {
        return call(() -> service.create(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable final String id) {
        return call(() -> service.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateById(@PathVariable final String id,
                                        @Valid @RequestBody final CustomerCredentialDto dto) {
        return call(() -> service.updateById(dto, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable final String id) {
        return call(() -> service.deleteById(id));
    }

    @DeleteMapping("customers/{customerId}")
    public ResponseEntity<?> deleteByCustomerId(@PathVariable final String customerId) {
        return call(() -> service.deleteByCustomerId(customerId));
    }
}
