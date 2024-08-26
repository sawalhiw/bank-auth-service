package org.bank.service;

import org.bank.dto.CustomerCredentialDto;

public interface CustomerCredentialService extends BaseService<CustomerCredentialDto> {
    CustomerCredentialDto findCustomerCredentialByUsername(final String username);

    CustomerCredentialDto deleteByCustomerId(String customerId);
}
