package org.bank.controller.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bank.dto.ErrorResponseDto;
import org.bank.service.implmentation.JwtServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    private static final Logger logger = LogManager.getLogger(JwtServiceImpl.class);


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponseDto> handle(final Exception exception) {
        logger.error("---------------------Exception------------------");
        logger.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto
                .builder()
                .message(exception.getMessage())
                .statusCode(400)
                .build());
    }
}
