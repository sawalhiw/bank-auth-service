package org.bank.controller;

import org.bank.service.AuthenticationService;
import org.bank.service.TokenValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {
        TokenValidationController.class,
        TokenValidationService.class
})
@RunWith(SpringRunner.class)
public class TokenValidationControllerTest extends BaseWebTest {
    @MockBean
    private TokenValidationService service;

    @Override
    protected String feature() {
        return "validate-token";
    }

    @Test
    public void testValidateTokenShouldSuccess() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Authorization", Collections.singletonList("Bearer jlasdlfjdaslkfdjaslfjkds"));
        testPostWithHeadersAndWithoutBody(status().isOk(), headers);
    }

    @Test
    public void testValidateTokenShouldFail() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        testPostWithHeadersAndWithoutBody(status().isBadRequest(), headers);
    }
}