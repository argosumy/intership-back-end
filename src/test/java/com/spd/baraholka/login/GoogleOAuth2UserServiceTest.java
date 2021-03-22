package com.spd.baraholka.login;

import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.login.service.OAuth2UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static com.spd.baraholka.login.service.GoogleOAuth2UserService.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GoogleOAuth2UserServiceTest {

    @Mock
    private Authentication authMock;

    @Mock
    private OAuth2User oAuth2UserMock;

    @Autowired
    @Qualifier("Google OAuth2")
    private OAuth2UserService serviceUnderTest;

    private final String dummyEmail = "mock@email.com";
    private final String dummyGivenName = "Mock Given Name";
    private final String dummyFamilyName = "Mock Family Name";
    private final String dummyPicture = "Mock Picture URL";

    private Map<String, Object> initDummyAttributes() {
        Map<String, Object> dummyAttributes = new HashMap<>();
        dummyAttributes.put(EMAIL_CLAIM_ATTRIBUTE, dummyEmail);
        dummyAttributes.put(FIRST_NAME_CLAIM_ATTRIBUTE, dummyGivenName);
        dummyAttributes.put(LAST_NAME_CLAIM_ATTRIBUTE, dummyFamilyName);
        dummyAttributes.put(AVATAR_CLAIM_ATTRIBUTE, dummyPicture);
        return dummyAttributes;
    }

    @BeforeEach
    public void initSecurityContext() {
        SecurityContextHolder.getContext().setAuthentication(authMock);
        when(authMock.getPrincipal()).thenReturn(oAuth2UserMock);
    }

    @Test
    @DisplayName("'Should return user's email from Principal")
    void shouldReturnUserEmailFromPrincipal() {
        when(oAuth2UserMock.getAttributes()).thenReturn(initDummyAttributes());
        OAuth2UserDTO oAuth2UserDto = serviceUnderTest.getOAuth2UserDTO();
        assertEquals(oAuth2UserDto.getEmail(), dummyEmail);
    }

    @Test
    @DisplayName("'Should return user's first name from Principal")
    void shouldReturnUserFirstNameFromPrincipal() {
        when(oAuth2UserMock.getAttributes()).thenReturn(initDummyAttributes());
        OAuth2UserDTO oAuth2UserDto = serviceUnderTest.getOAuth2UserDTO();
        assertEquals(oAuth2UserDto.getFirstName(), dummyGivenName);
    }

    @Test
    @DisplayName("'Should return user's last name from Principal")
    void shouldReturnUserLastNameFromPrincipal() {
        when(oAuth2UserMock.getAttributes()).thenReturn(initDummyAttributes());
        OAuth2UserDTO oAuth2UserDto = serviceUnderTest.getOAuth2UserDTO();
        assertEquals(oAuth2UserDto.getLastName(), dummyFamilyName);
    }

    @Test
    @DisplayName("'Should return user's avatar from Principal")
    void shouldReturnUserAvatarFromPrincipal() {
        when(oAuth2UserMock.getAttributes()).thenReturn(initDummyAttributes());
        OAuth2UserDTO oAuth2UserDto = serviceUnderTest.getOAuth2UserDTO();
        assertEquals(oAuth2UserDto.getAvatar(), dummyPicture);
    }

    @AfterEach
    void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }
}
