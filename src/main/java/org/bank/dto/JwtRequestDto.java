package org.bank.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class JwtRequestDto {
    @NotBlank(message = "password shouldn't be null or empty.")
    private String password;
    @NotBlank(message = "username shouldn't be null or empty.")
    private String username;
}
