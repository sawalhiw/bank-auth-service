package org.bank.controller;

import org.bank.dto.CustomerCredentialDto;
import org.bank.dto.Role;
import org.bank.service.CustomerCredentialService;
import org.bank.service.TokenValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = {
        CustomerCredentialController.class,
        CustomerCredentialService.class
})
@RunWith(SpringRunner.class)
public class CustomerCredentialControllerTest extends BaseWebTest<CustomerCredentialDto> {
    @MockBean
    public CustomerCredentialService service;
    private String customerCredentialId = UUID.randomUUID().toString();

    @Override
    protected String feature() {
        return "credentials";
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        testDelete(customerCredentialId);
    }

    @Test
    public void testFindById() throws Exception {
        testGetById(customerCredentialId);
    }

    @Test
    public void testUpdateCustomerShouldReturnBadRequest() throws Exception {
        final CustomerCredentialDto dto = CustomerCredentialDto
                .builder()
                .id(customerCredentialId)
                .build();
        testPut(customerCredentialId, dto, status().isBadRequest());
    }

    @Test
    public void testUpdateCustomerShouldReturnOk() throws Exception {
        final CustomerCredentialDto dto = CustomerCredentialDto
                .builder()
                .id(customerCredentialId)
                .customerId("1234566")
                .username("admin.admin")
                .password("V1p_admin")
                .role(Role.ADMIN)
                .build();
        testPut(customerCredentialId, dto, status().isOk());
    }

    @Test
    public void testCreateCustomerShouldReturnOk() throws Exception {
        final CustomerCredentialDto dto = CustomerCredentialDto
                .builder()
                .id(customerCredentialId)
                .customerId("1234566")
                .username("admin.admin")
                .password("V1p_admin")
                .role(Role.ADMIN)
                .build();
        testPost(dto, status().isOk());
    }

    @Test
    public void testCreateCustomerShouldReturnBadRequest() throws Exception {
        CustomerCredentialDto dto = CustomerCredentialDto
                .builder()
                .id(customerCredentialId)
                .build();
        testPost(dto, status().isBadRequest());
    }
}