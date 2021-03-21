package com.spd.baraholka.user;

import com.github.database.rider.core.api.dataset.DataSet;
import com.spd.baraholka.role.Role;
import com.spd.baraholka.user.persistance.entities.User;
import com.spd.baraholka.role.UserAuthority;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.spd.baraholka.role.Role.MODERATOR;
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
        dummyUser.setAvatar(dummyAvatar);
        dummyUser.setLocation("");
        dummyUser.setPosition("");
        dummyUser.setPhoneNumber("");
        return dummyUser;
    }

    @Test
    void shouldGrantModeratorRole() {
        User dummyUser = initDummyUser();
        assertFalse(dummyUser.getRoles().contains(Role.MODERATOR));
        dummyUser.grantRole(Role.MODERATOR);
        assertTrue(dummyUser.getRoles().contains(Role.MODERATOR));
    }

    @Test
    void shouldHaveRoleUserByDefault() {
        User dummyUser = initDummyUser();
        assertTrue(dummyUser.getRoles().contains(Role.USER));
        assertFalse(dummyUser.getRoles().contains(Role.MODERATOR));
    }
}
