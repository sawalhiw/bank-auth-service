package org.bank.service.implmentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bank.dto.CustomerCredentialDto;
import org.bank.dto.CustomerDto;
import org.bank.dto.FeatureInfoDto;
import org.bank.entity.CustomerCredential;
import org.bank.exception.DuplicateValueException;
import org.bank.exception.NotFoundException;
import org.bank.feign.BankCustomerClient;
import org.bank.mapper.CustomerCredentialMapper;
import org.bank.repository.CustomerCredentialRepository;
import org.bank.service.CustomerCredentialService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerCredentialServiceImpl extends BaseServiceImpl<CustomerCredential, CustomerCredentialDto>
        implements CustomerCredentialService {
    private static final Logger logger = LogManager.getLogger(BaseServiceImpl.class);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final CustomerCredentialRepository repository;
    private final BankCustomerClient customerClient;

    public CustomerCredentialServiceImpl(CustomerCredentialRepository repository,
                                         CustomerCredentialMapper mapper,
                                         BankCustomerClient customerClient) {
        super(repository, mapper);
        this.repository = repository;
        this.customerClient = customerClient;
    }

    @Override
    protected FeatureInfoDto featureInfo() {
        return FeatureInfoDto
                .builder()
                .single("Customer Credential")
                .plural("Customer Credentials")
                .build();
    }

    @Override
    public CustomerCredentialDto create(final CustomerCredentialDto dto) {
        validateCustomerExistence(dto.getCustomerId());
        dto.setPassword(encoder.encode(dto.getPassword()));
        return super.create(dto);
    }

    @Override
    public CustomerCredentialDto findCustomerCredentialByUsername(String username) {
        return mapper.toDto(repository
                .findCustomerCredentialByUsername(username)
                .orElseThrow(() -> new NotFoundException(String
                        .format("No user found with the username: %s. Please ensure the username is correct.", username))));
    }

    @Override
    public CustomerCredentialDto deleteByCustomerId(String customerId) {
        final CustomerCredentialDto response = mapper.toDto(repository
                .findCustomerCredentialByCustomerId(customerId)
                .orElseThrow(() -> new NotFoundException(String
                        .format("No user found with the customerId: %s. Please ensure the customerId is correct.", customerId))));
        repository.deleteCustomerCredentialByCustomerId(customerId);
        logger.info("Deleting customer credentials.");
        return response;
    }

    private void validateCustomerExistence(final String customerId) {
        logger.info("Checking customer existence.");
        final CustomerDto customerDto = customerClient.getCustomer(customerId);
        if (Objects.isNull(customerDto)) {
            throw new NotFoundException(String
                    .format("No user found with the customerId: %s. Please ensure the customerId is correct.", customerId));
        }
    }

    @Override
    protected void validate(CustomerCredentialDto dto) {
        if (repository.existsCustomerCredentialByCustomerId(dto.getCustomerId())) {
            throw new DuplicateValueException("CustomerId already exists.");
        }

        if (repository.existsCustomerCredentialByUsername(dto.getUsername())) {
            throw new DuplicateValueException("Username already exists.");
        }
    }
}
