package com.spd.baraholka.login;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.DBUnitExtension;
import com.spd.baraholka.login.controller.OAuth2AuthenticationSuccessHandler;
import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.login.service.OAuth2UserService;
import com.spd.baraholka.user.controller.mappers.UserMapper;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.user.service.UserService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ExtendWith(DBUnitExtension.class)
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE, schema = "public")
class OAuth2AuthenticationSuccessHandlerIntegrationTest {

    private final String dummyEmail = "new_dummy@email.com";
    private final String dummyGivenName = "Mock Given Name";
    private final String dummyFamilyName = "Mock Family Name";
    private final String dummyPicture = "Mock Picture URL";
    private final String existingEmail = "existing@email.com";

    private final User dummyUser = new User();
    private final OAuth2UserDTO dummyOAuth2UserDTO = new OAuth2UserDTO(dummyEmail, dummyGivenName, dummyFamilyName, dummyPicture);

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
        ReflectionTestUtils.setField(oAuth2SuccessHandlerUnderTest, "allowedDomains", Lists.newArrayList("spd-ukraine.com", "email.com"));
    }

    private OAuth2UserDTO initExistingDummyOAuth2UserDto() {
        return new OAuth2UserDTO(existingEmail, dummyGivenName, dummyFamilyName, dummyPicture);
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

    @Test
    @DisplayName("'Should save a new successfully logged-in user into a database")
    @DataSet(value = "/dbunit/users.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void shouldSaveNewLoggedInUserToDbTest() throws IOException {
        when(oAuth2UserService.getUserInfoFromOAuth2(any(Authentication.class))).thenReturn(dummyOAuth2UserDTO);
        when(userMapper.convertFromOAuth(dummyOAuth2UserDTO)).thenReturn(dummyUser);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        oAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(request, response, mock(Authentication.class));

        verify(userMapper, times(1)).convertFromOAuth(dummyOAuth2UserDTO);
        verify(userService, times(1)).create(dummyUser);
        assertTrue(userService.existsByEmail(dummyUser.getEmail()));
    }

    @Test
    @DisplayName("'Should not save an existing successfully logged-in user into a database")
    @DataSet(value = "/dbunit/users.yml", strategy = SeedStrategy.CLEAN_INSERT)
    @ExpectedDataSet(value = "/dbunit/users.yml")
    void shouldNotSaveExistingLoggedInUserToDbTest() throws IOException {
        OAuth2UserDTO dummyOAuth2UserDTO = initExistingDummyOAuth2UserDto();
        dummyUser.setEmail(existingEmail);
        when(oAuth2UserService.getUserInfoFromOAuth2(any(Authentication.class))).thenReturn(dummyOAuth2UserDTO);
        when(userMapper.convertFromOAuth(dummyOAuth2UserDTO)).thenReturn(dummyUser);

        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        oAuth2SuccessHandlerUnderTest.onAuthenticationSuccess(request, response, mock(Authentication.class));

        verify(userMapper, times(0)).convertFromOAuth(dummyOAuth2UserDTO);
        verify(userService, times(0)).create(dummyUser);
    }
}
