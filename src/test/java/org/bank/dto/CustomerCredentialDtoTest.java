package org.bank.dto;

import org.bank.constants.Constants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerCredentialDtoTest {
    private CustomerCredentialDto customerCredentialDto;

    @Before
    public void setUp() {
        customerCredentialDto = CustomerCredentialDto.builder()
                .password("P@ssw0rd123")
                .username("john_doe")
                .customerId("CUST123")
                .role(Role.CUSTOMER)
                .build();
    }

    @Test
    public void testGetPasswordHash_CalculatesHashWhenNull() {
        // Act
        String passwordHash = customerCredentialDto.getPasswordHash();

        // Assert
        assertNotNull(passwordHash);
        assertTrue(passwordHash.startsWith("$2a$")); // Assuming BCrypt is used for hashing
    }

    @Test
    public void testGetPasswordHash_ReturnsExistingHashWhenNotNull() {
        // Arrange
        String expectedHash = Constants.encoder.encode("P@ssw0rd123");
        customerCredentialDto.passwordHash = expectedHash;

        // Act
        String passwordHash = customerCredentialDto.getPasswordHash();

        // Assert
        assertNotNull(passwordHash);
        assertEquals(expectedHash, passwordHash);
    }
}