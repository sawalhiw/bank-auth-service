package org.bank.config;

import org.bank.controller.AuthenticationController;
import org.bank.controller.CustomerCredentialController;
import org.bank.controller.TokenValidationController;
import org.bank.repository.CustomerCredentialRepository;
import org.bank.service.AuthenticationService;
import org.bank.service.CustomerCredentialService;
import org.bank.service.TokenValidationService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("services")
public class ServicesConfiguration {

    @Bean
    public AuthenticationController authenticationController() {
        return Mockito.mock(AuthenticationController.class);
    }

    @Bean
    public AuthenticationService authenticationService() {
        return Mockito.mock(AuthenticationService.class);
    }

    @Bean
    public CustomerCredentialController customerCredentialController() {
        return Mockito.mock(CustomerCredentialController.class);
    }

    @Bean
    public CustomerCredentialService customerCredentialService() {
        return Mockito.mock(CustomerCredentialService.class);
    }

    @Bean
    public CustomerCredentialRepository customerCredentialRepository() {
        return Mockito.mock(CustomerCredentialRepository.class);
    }

    @Bean
    public TokenValidationController tokenValidationController() {
        return Mockito.mock(TokenValidationController.class);
    }

    @Bean
    public TokenValidationService tokenValidationService() {
        return Mockito.mock(TokenValidationService.class);
    }
}
