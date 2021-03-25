package com.spd.baraholka.user;

import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.persistance.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
class UserTest {

    private User initDummyUser() {
        String dummyEmail = "mock@email.com";
        String dummyFirstName = "Mock Given Name";
        String dummyLastName = "Mock Family Name";
        String dummyAvatar = "Mock Picture URL";
        User dummyUser = new User();
        dummyUser.setFirstName(dummyFirstName);
        dummyUser.setLastName(dummyLastName);
        dummyUser.setEmail(dummyEmail);
        dummyUser.setImageUrl(dummyAvatar);
        dummyUser.setLocation("");
        dummyUser.setPosition("");
        dummyUser.setPhoneNumber("");
        return dummyUser;
    }

    @Test
    void shouldGrantModeratorRole() {
        User dummyUser = initDummyUser();
        assertFalse(dummyUser.getRoles().contains(Role.ROLE_MODERATOR));
        dummyUser.grantRole(Role.ROLE_MODERATOR);
        assertTrue(dummyUser.getRoles().contains(Role.ROLE_MODERATOR));
    }

    @Test
    void shouldHaveRoleUserByDefault() {
        User dummyUser = initDummyUser();
        assertTrue(dummyUser.getRoles().contains(Role.ROLE_USER));
        assertFalse(dummyUser.getRoles().contains(Role.ROLE_MODERATOR));
    }
}
