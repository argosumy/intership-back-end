package com.spd.baraholka.user;

import com.spd.baraholka.login.controller.dto.OAuth2UserDTO;
import com.spd.baraholka.user.controller.mappers.UserMapper;
import com.spd.baraholka.user.persistance.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@ActiveProfiles("test")
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapperUnderTest;

    private OAuth2UserDTO dummyOAuth2UserDTO;

    @BeforeEach
    void initDummyOAuth2UserDto() {
        String dummyEmail = "mock@email.com";
        String dummyGivenName = "Mock Given Name";
        String dummyFamilyName = "Mock Family Name";
        String dummyPicture = "Mock Picture URL";
        dummyOAuth2UserDTO = new OAuth2UserDTO(dummyEmail, dummyGivenName, dummyFamilyName, dummyPicture);
    }

    @Test
    @DisplayName("'Should convert OAuth2UserDto to User")
    void shouldConvertOAuth2UserDtoToUser() {
        User dummyUser = userMapperUnderTest.convertFromOAuth(dummyOAuth2UserDTO);

        assertEquals(dummyOAuth2UserDTO.getEmail(), dummyUser.getEmail());
        assertEquals(dummyOAuth2UserDTO.getFirstName(), dummyUser.getFirstName());
        assertEquals(dummyOAuth2UserDTO.getLastName(), dummyUser.getLastName());
        assertEquals(dummyOAuth2UserDTO.getAvatar(), dummyUser.getAvatar());
        assertEquals("", dummyUser.getLocation());
        assertEquals("", dummyUser.getPosition());
        assertEquals("", dummyUser.getPhoneNumber());
    }
}
