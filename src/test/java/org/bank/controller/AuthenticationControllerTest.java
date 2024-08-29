package org.bank.controller;


import org.bank.dto.JwtRequestDto;
import org.bank.service.AuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {
        AuthenticationController.class,
        AuthenticationService.class
})
@RunWith(SpringRunner.class)
public class AuthenticationControllerTest extends BaseWebTest<JwtRequestDto> {
    @MockBean
    private AuthenticationService service;

    @Override
    protected String feature() {
        return "login";
    }

    @Test
    public void testLoginApiShouldSuccess() throws Exception {
        JwtRequestDto dto = JwtRequestDto
                .builder()
                .username("admin")
                .password("admin")
                .build();
        testPost(dto, status().isOk());
    }

    @Test
    public void testLoginApiShouldFail() throws Exception {
        JwtRequestDto dto = JwtRequestDto
                .builder()
                .password("admin")
                .build();
        testPost(dto, status().isBadRequest());
    }
}