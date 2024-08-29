package org.bank.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.bank.constants.Constants;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class CustomerCredentialDto extends BaseDto {
    @NotBlank(message = "Password shouldn't be null or empty.")
    @Size(min = 6, max = 20, message = "Password should be greater than 5 and lower than 20.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).*$",
            message = "Password must contain at least one special character and one number.")
    private String password;

    @NotBlank(message = "Username shouldn't be null or empty")
    @Size(min = 6, max = 20)
    private String username;

    @NotBlank(message = "CustomerId shouldn't be null or empty.")
    private String customerId;

    @NotNull(message = "Role shouldn't be null.")
    private Role role;

    @JsonIgnore
    public String passwordHash;

    public String getPasswordHash() {
        if (Objects.isNull(passwordHash)) {
            this.passwordHash = Constants.encoder.encode(password);
        }
        return passwordHash;
    }
}
