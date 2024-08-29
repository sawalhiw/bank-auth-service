package org.bank.service.implmentation;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bank.constants.Constants;
import org.bank.dto.CustomerCredentialDto;
import org.bank.dto.CustomerDto;
import org.bank.dto.JwtRequestDto;
import org.bank.dto.JwtResponseDto;
import org.bank.feign.BankCustomerClient;
import org.bank.service.AuthenticationService;
import org.bank.service.CustomerCredentialService;
import org.bank.utils.util.JwtUtils;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements AuthenticationService {
    private static final Logger logger = LogManager.getLogger(JwtServiceImpl.class);
    private final CustomerCredentialService service;
    private final BankCustomerClient client;

    @Override
    public JwtResponseDto login(JwtRequestDto jwtRequestDto) {
        if (Objects.isNull(jwtRequestDto)) {
            throw new AuthenticationCredentialsNotFoundException("Authentication failed: No credentials were provided. Please ensure that both username and password are included in the request.");
        }
        CustomerCredentialDto storedCredential = service.findCustomerCredentialByUsername(jwtRequestDto.getUsername());

        validateCredential(jwtRequestDto.getPassword(), storedCredential.getPasswordHash());

        final CustomerDto customerDetails = client.getCustomer(storedCredential.getCustomerId());
        customerDetails.setUsername(jwtRequestDto.getUsername());
        customerDetails.setRole(storedCredential.getRole());

        return JwtUtils.generateToken(customerDetails);
    }

    public void validateCredential(final String receivedPassword,
                                   final String storedPasswordHash) {
        logger.info("Validating User Credentials.");
        if (!Constants.encoder.matches(receivedPassword, storedPasswordHash)) {
            throw new BadCredentialsException("Authentication failed: The username or password you entered is incorrect. Please double-check your credentials and try again.");
        }
    }
}
