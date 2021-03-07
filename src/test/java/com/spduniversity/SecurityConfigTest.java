package com.spduniversity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithAnonymousUser
    void shouldReturnOkStatusOnLoginPage() throws Exception {
        mvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void shouldRedirectToGoogleOAuth2Server() throws Exception {
        String appGoogleOAuth2ClientEndpoint = "/oauth2/authorization/google";
        String googleOAuth2ServerEndpoint = "https://accounts.google.com/o/oauth2/v2/";
        mvc.perform(get(appGoogleOAuth2ClientEndpoint))
                .andExpect(redirectedUrlPattern(googleOAuth2ServerEndpoint + "**"))
                .andExpect(status().isFound());
    }

    @Test
    @WithMockUser
    void shouldReturnOkStatusOnLogoutPage() throws Exception {
        mvc.perform(get("/logout"))
                .andExpect(status().isOk());
    }
}
