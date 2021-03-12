package com.spd.baraholka.login;

import com.spd.baraholka.login.dto.OAuth2UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
        dummyAttributes.put("email", dummyEmail);
        dummyAttributes.put("given_name", dummyGivenName);
        dummyAttributes.put("family_name", dummyFamilyName);
        dummyAttributes.put("picture", dummyPicture);
        return dummyAttributes;
    }

    @BeforeEach
    public void initSecurityContext() {
        SecurityContextHolder.getContext().setAuthentication(authMock);
        when(authMock.getPrincipal()).thenReturn(oAuth2UserMock);
    }

    @Test
    void shouldReturnUserInfoFromPrincipal() {
        when(oAuth2UserMock.getAttributes()).thenReturn(initDummyAttributes());

        OAuth2UserDto oAuth2UserDto = serviceUnderTest.getUserInfoFromOAuth2();

        assertEquals(oAuth2UserDto.getEmail(), dummyEmail);
        assertEquals(oAuth2UserDto.getFirstName(), dummyGivenName);
        assertEquals(oAuth2UserDto.getLastName(), dummyFamilyName);
        assertEquals(oAuth2UserDto.getAvatar(), dummyPicture);
    }

    @AfterEach
    public void clearSecurityContext() {
        SecurityContextHolder.clearContext();
    }
}
