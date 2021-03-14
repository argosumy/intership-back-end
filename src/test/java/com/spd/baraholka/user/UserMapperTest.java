package com.spd.baraholka.user;

import com.spd.baraholka.login.dto.OAuth2UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapperUnderTest;

    private OAuth2UserDto initDummyOAuth2UserDto() {
        String dummyEmail = "mock@email.com";
        String dummyGivenName = "Mock Given Name";
        String dummyFamilyName = "Mock Family Name";
        String dummyPicture = "Mock Picture URL";
        return new OAuth2UserDto(dummyEmail, dummyGivenName, dummyFamilyName, dummyPicture);
    }

    @Test
    void shouldConvertOAuth2UserDtoToUser() {
        OAuth2UserDto dummyOAuth2UserDto = initDummyOAuth2UserDto();
        User dummyUser = userMapperUnderTest.convertToEntity(dummyOAuth2UserDto);

        assertEquals(dummyOAuth2UserDto.getEmail(), dummyUser.getEmail());
        assertEquals(dummyOAuth2UserDto.getFirstName(), dummyUser.getFirstName());
        assertEquals(dummyOAuth2UserDto.getLastName(), dummyUser.getLastName());
        assertEquals(dummyOAuth2UserDto.getAvatar(), dummyUser.getAvatar());
        assertEquals("", dummyUser.getLocation());
        assertEquals("", dummyUser.getPosition());
        assertEquals("", dummyUser.getPhoneNumber());
    }
}
