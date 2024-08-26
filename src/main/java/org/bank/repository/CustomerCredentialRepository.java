package org.bank.repository;

import jakarta.transaction.Transactional;
import org.bank.entity.CustomerCredential;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerCredentialRepository extends BaseRepository<CustomerCredential> {
    Optional<CustomerCredential> findCustomerCredentialByUsername(final String username);

    Optional<CustomerCredential> findCustomerCredentialByCustomerId(final String customerId);

    @Modifying
    @Transactional
    void deleteCustomerCredentialByCustomerId(String customerId);
}
