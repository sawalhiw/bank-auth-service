package org.bank.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorResponseDto {
    private String stacktrace;
    private String message;
    private Integer statusCode;
}
