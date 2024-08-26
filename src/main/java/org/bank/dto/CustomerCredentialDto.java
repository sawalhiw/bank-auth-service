package org.bank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class CustomerCredentialDto extends BaseDto {
    @NotBlank
    @Size(min = 6, max = 20, message = "Password should be greater than 5 and lower than 20.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).*$",
            message = "Password must contain at least one special character and one number.")
    private String password;

    @NotBlank
    @Size(min = 6, max = 20)
    private String username;

    @NotBlank
    @Pattern(regexp = "\\d{7}", message = "Customer ID must be exactly 7 digits.")
    private String customerId;

    @NotBlank
    private Role role;
}
