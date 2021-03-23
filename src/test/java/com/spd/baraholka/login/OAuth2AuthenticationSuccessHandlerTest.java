package com.spd.baraholka.login;

import com.github.database.rider.junit5.DBUnitExtension;
import com.spd.baraholka.config.exceptions.OAuth2ProcessingException;
import com.spd.baraholka.login.controller.OAuth2AuthenticationSuccessHandler;
import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.login.service.GoogleOAuth2UserService;
import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.service.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static com.spd.baraholka.login.service.GoogleOAuth2UserService.DOMAIN_NOT_ALLOWED;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@Disabled
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(DBUnitExtension.class)
class OAuth2AuthenticationSuccessHandlerTest {

    private final String dummyEmail = "new_dummy@email.com";
    private final String dummyGivenName = "Mock Given Name";
    private final String dummyFamilyName = "Mock Family Name";
    private final String dummyPicture = "Mock Picture URL";

    private OAuth2UserDTO dummyOAuth2UserDTO;

    @Spy
    private final User dummyUser = new User();

    @Mock
    private UserService userService;

    @Mock
    private GoogleOAuth2UserService oAuth2UserService;

//    @InjectMocks
    private OAuth2AuthenticationSuccessHandler oAuth2SuccessHandlerUnderTest;

    @BeforeEach
    void init() {
        oAuth2SuccessHandlerUnderTest = new OAuth2AuthenticationSuccessHandler();
    }

    @BeforeEach
    void initDummyUser() {
        dummyUser.setEmail(dummyEmail);
        dummyUser.setFirstName(dummyGivenName);
        dummyUser.setLastName(dummyFamilyName);
        dummyUser.setAvatar(dummyPicture);
        dummyUser.setLocation("");
        dummyUser.setPhoneNumber("");
        dummyUser.setPosition("");
    }

    @BeforeEach
    void initDummyOAuth2UserDto() {
        dummyOAuth2UserDTO = new OAuth2UserDTO(dummyEmail, dummyGivenName, dummyFamilyName, dummyPicture);
    }

    private OAuth2UserDTO initNotExistingDummyAllowedDomainOAuth2UserDto() {
        String notExistingEmail = "notExisting@email.com";
        return new OAuth2UserDTO(notExistingEmail, dummyGivenName, dummyFamilyName, dummyPicture);
    }

    private OAuth2UserDTO initExistingDummyOAuth2UserDto() {
        String existingEmail = "existing@email.com";
        return new OAuth2UserDTO(existingEmail, dummyGivenName, dummyFamilyName, dummyPicture);
    }

    private OAuth2UserDTO initNotAllowedDummyOAuth2UserDto() {
        String notAllowedEmail = "email@notAllowedDomain.com";
        return new OAuth2UserDTO(notAllowedEmail, dummyGivenName, dummyFamilyName, dummyPicture);
    }

    @Test
    @DisplayName("'Should invoke creating of a new successfully logged-in user")
    void shouldInvokeCreateNewLoggedInUserTest() throws IOException {
        OAuth2UserDTO dummyOAuth2UserDTO = initNotExistingDummyAllowedDomainOAuth2UserDto();
        when(oAuth2UserService.getOAuth2UserDTO(any(Authentication.class))).thenReturn(dummyOAuth2UserDTO);
        when(userService.existsByEmail(dummyOAuth2UserDTO.getEmail())).thenReturn(false);
        when(userService.convertFromOAuth(dummyOAuth2UserDTO)).thenReturn(dummyUser);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        oAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(request, response, mock(Authentication.class));

        verify(userService, times(1)).convertFromOAuth(dummyOAuth2UserDTO);
        verify(userService, times(1)).create(dummyUser);
    }

    @Test
    @DisplayName("'Should not invoke creating of an existing successfully logged-in user")
    void shouldNotInvokeCreateExistingLoggedInUserTest() throws IOException {
        OAuth2UserDTO dummyOAuth2UserDTO = initExistingDummyOAuth2UserDto();

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        oAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(request, response, mock(Authentication.class));

        verify(userService, times(0)).convertFromOAuth(dummyOAuth2UserDTO);
        verify(userService, times(0)).create(dummyUser);
    }

    @Test
    @DisplayName("Should grant a 'Moderator' role to a first logged-in user")
    void shouldGrantModeratorRoleToFirstLoggedInUser() throws IOException {
        when(oAuth2UserService.getOAuth2UserDTO(any(Authentication.class))).thenReturn(dummyOAuth2UserDTO);
        when(userService.existsByEmail(dummyUser.getEmail())).thenReturn(false);
        when(userService.count()).thenReturn(0);
        when(userService.convertFromOAuth(dummyOAuth2UserDTO)).thenReturn(dummyUser);
        assertFalse(dummyUser.getRoles().contains(Role.ROLE_MODERATOR));

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        oAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(request, response, mock(Authentication.class));

        verify(dummyUser, times(1)).grantRole(Role.ROLE_MODERATOR);
        assertTrue(dummyUser.getRoles().contains(Role.ROLE_MODERATOR));
    }

    @Test
    @DisplayName("Should not grant a 'Moderator' role to a not first logged-in user")
    void shouldNotGrantModeratorRoleToNotFirstLoggedInUser() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        oAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(request, response, mock(Authentication.class));

        verify(dummyUser, times(0)).grantRole(Role.ROLE_MODERATOR);
        assertFalse(dummyUser.getRoles().contains(Role.ROLE_MODERATOR));
    }

    @Test
    @DisplayName("'Should throw OAuth2ProcessingException when a user's email domain is not in 'login.allowed-domains' app property")
    void shouldThrowExceptionForNotAllowedDomain() {
        OAuth2UserDTO dummyOAuth2UserDTO = initNotAllowedDummyOAuth2UserDto();
        when(oAuth2UserService.getOAuth2UserDTO(any(Authentication.class))).thenReturn(dummyOAuth2UserDTO);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        Exception exception = Assertions.assertThrows(OAuth2ProcessingException.class,
                () -> oAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(request, response, mock(Authentication.class))
                );
        String expectedMessage = DOMAIN_NOT_ALLOWED;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
