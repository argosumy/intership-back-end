package com.spd.baraholka.login;

import com.github.database.rider.junit5.DBUnitExtension;
import com.spd.baraholka.login.controller.OAuth2AuthenticationSuccessHandler;
import com.spd.baraholka.login.dto.OAuth2UserDto;
import com.spd.baraholka.login.service.OAuth2UserService;
import com.spd.baraholka.user.User;
import com.spd.baraholka.user.UserMapper;
import com.spd.baraholka.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(DBUnitExtension.class)
class OAuth2AuthenticationSuccessHandlerTest {

    private final String dummyEmail = "new_dummy@email.com";
    private final String dummyGivenName = "Mock Given Name";
    private final String dummyFamilyName = "Mock Family Name";
    private final String dummyPicture = "Mock Picture URL";

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private OAuth2UserService oAuth2UserService;

    @InjectMocks
    private OAuth2AuthenticationSuccessHandler oAuth2SuccessHandlerUnderTest;

    @BeforeEach
    void init() {
        oAuth2SuccessHandlerUnderTest = new OAuth2AuthenticationSuccessHandler(userService, userMapper, oAuth2UserService);
    }

    private OAuth2UserDto initDummyOAuth2UserDto() {
        return new OAuth2UserDto(dummyEmail, dummyGivenName, dummyFamilyName, dummyPicture);
    }

    private User initDummyUser() {
        User dummyUser = new User();
        dummyUser.setEmail(dummyEmail);
        dummyUser.setFirstName(dummyGivenName);
        dummyUser.setLastName(dummyFamilyName);
        dummyUser.setAvatar(dummyPicture);
        dummyUser.setLocation("");
        dummyUser.setPhoneNumber("");
        dummyUser.setPosition("");
        return dummyUser;
    }

    @Test
    void shouldInvokeCreateNewLoggedInUserTest() throws IOException {
        OAuth2UserDto dummyOAuth2UserDto = initDummyOAuth2UserDto();
        User dummyUser = initDummyUser();
        when(oAuth2UserService.getUserInfoFromOAuth2(any(Authentication.class))).thenReturn(dummyOAuth2UserDto);
        when(userService.existsByEmail(dummyOAuth2UserDto.getEmail())).thenReturn(false);
        when(userMapper.convertToEntity(dummyOAuth2UserDto)).thenReturn(dummyUser);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        oAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(request, response, mock(Authentication.class));

        verify(userMapper, times(1)).convertToEntity(dummyOAuth2UserDto);
        verify(userService, times(1)).create(dummyUser);
    }

    @Test
    void shouldNotInvokeCreateExistingLoggedInUserTest() throws IOException {
        OAuth2UserDto dummyOAuth2UserDto = initDummyOAuth2UserDto();
        User dummyUser = initDummyUser();
        when(oAuth2UserService.getUserInfoFromOAuth2(any(Authentication.class))).thenReturn(dummyOAuth2UserDto);
        when(userService.existsByEmail(dummyOAuth2UserDto.getEmail())).thenReturn(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        oAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(request, response, mock(Authentication.class));

        verify(userMapper, times(0)).convertToEntity(dummyOAuth2UserDto);
        verify(userService, times(0)).create(dummyUser);
    }
}
