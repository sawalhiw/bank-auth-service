package org.bank.controller.base;

import org.bank.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponseDto> handle(final Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto
                .builder()
                .message(exception.getMessage())
                .stacktrace(Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.joining()))
                .statusCode(400)
                .build());
    }
}
