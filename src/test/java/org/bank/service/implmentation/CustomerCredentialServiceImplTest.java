package org.bank.service.implmentation;


import org.bank.dto.CustomerCredentialDto;
import org.bank.dto.CustomerDto;
import org.bank.entity.CustomerCredential;
import org.bank.exception.NotFoundException;
import org.bank.feign.BankCustomerClient;
import org.bank.mapper.CustomerCredentialMapper;
import org.bank.repository.CustomerCredentialRepository;
import org.bank.service.BaseServiceTest;
import org.bank.service.CustomerCredentialService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"services"})
public class CustomerCredentialServiceImplTest extends BaseServiceTest<CustomerCredential, CustomerCredentialDto> {
    @MockBean
    public CustomerCredentialMapper mapper;
    @MockBean
    public CustomerCredentialRepository repository;
    @Mock
    public BankCustomerClient client;
    private CustomerCredentialServiceImpl service;

    @Override
    protected CustomerCredential createEntity() {
        return CustomerCredential.builder().id("1").build();
    }

    @Override
    protected CustomerCredentialDto createDto() {
        return CustomerCredentialDto.builder().id("1").build();
    }

    @Before
    public void setup() {
        service = new CustomerCredentialServiceImpl(repository, mapper, client);
        setup(mapper, repository, service);
    }

    @Test
    public void testFindAll() {
        super.testFindAll();
    }

    @Test
    public void testDeleteById() {
        super.testDeleteById();
    }

    @Test
    public void testFindById() {
        super.testFindById();
    }

    @Test
    public void testCreateShouldSuccess() {
        when(client.getCustomer(any())).thenReturn(CustomerDto.builder().build());
        super.testCreate();
    }

    @Test(expected = NotFoundException.class)
    public void testCreateShouldFail() {
        super.testCreate();
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateByIdShouldThrowNotFoundException() {
        super.testUpdate();
    }

    @Test
    public void testUpdateByIdShouldNotFail() {
        when(repository.existsById(any())).thenReturn(true);
        super.testUpdate();
    }

    @Test(expected = NotFoundException.class)
    public void testFindCustomerCredentialByUsernameShouldFail() {
        service.findCustomerCredentialByUsername("admin");
    }

    @Test
    public void testFindCustomerCredentialByUsernameShouldNotFail() {
        when(repository.findCustomerCredentialByUsername(any())).thenReturn(Optional.of(CustomerCredential
                .builder()
                .username("admin")
                .build()));
        service.findCustomerCredentialByUsername("admin");
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteCustomerCredentialByCustomerIdShouldFail() {
        when(repository.findCustomerCredentialByCustomerId(any())).thenReturn(Optional.empty());
        service.deleteByCustomerId("id");
    }

    @Test
    public void testDeleteCustomerCredentialByCustomerIdShouldNotFail() {
        when(repository.findCustomerCredentialByCustomerId(any())).thenReturn(Optional.of(
                CustomerCredential.builder().customerId("id").build()
        ));
        service.deleteByCustomerId("id");
    }
}