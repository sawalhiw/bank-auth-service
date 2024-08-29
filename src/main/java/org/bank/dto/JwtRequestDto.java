package org.bank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class JwtRequestDto {
    @NotBlank
    private String password;
    @NotBlank
    private String username;
}
