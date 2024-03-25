package org.example.integration.http.controller;


import lombok.RequiredArgsConstructor;
import org.example.database.entity.Role;
import org.example.integration.IntegrationTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.example.dto.UserCreateEditDto.Fields.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;

    @BeforeEach
    void init() {
        List<GrantedAuthority> roles = Arrays.asList(Role.ADMIN, Role.USER);
        User testUser = new User("test@gmail.com", "test", roles);
        TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken(testUser, testUser.getPassword(), roles);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authenticationToken);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users")
                        .with(user("test@gmail.com").authorities(Role.ADMIN)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/users")
                        .param(username, "test@gmail.com")
                        .param(firstname, "Test")
                        .param(lastname, "TestTest")
                        .param(role, "ADMIN")
                        .param(companyId, "1")
                        .param(birthDate, "2000-01-01")
                )
                .andExpectAll(
                        status().is3xxRedirection(),
                        redirectedUrlPattern("/users/{\\d+}")
                );
    }


}
