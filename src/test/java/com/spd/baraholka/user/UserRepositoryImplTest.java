package com.spd.baraholka.user;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(DBUnitExtension.class)
@ExtendWith(MockitoExtension.class)
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE, schema="public")
class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepositoryUnderTest;

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
    @DisplayName("'Should return 'true' when a user with a certain email exists in a database")
    @DataSet(value="/dbunit/users.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void shouldReturnTrueWhenUserWithEmailExists() {
        String existingEmail = "existing@email.com";
        boolean exists = userRepositoryUnderTest.existsByEmail(existingEmail);
        assertTrue(exists);
    }

    @Test
    @DisplayName("'Should return 'false' when a user with a certain email does not exist in a database")
    @DataSet(value="/dbunit/users.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void shouldReturnFalseWhenUserWithEmailDoesntExist() {
        String newEmail = "new@email.com";
        boolean exists = userRepositoryUnderTest.existsByEmail(newEmail);
        assertFalse(exists);
    }

    @Test
    @DataSet(value="/dbunit/users.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void shouldReturnNumberOfAllUsers() {
        int numberOfUsers = userRepositoryUnderTest.countAll();
        assertEquals(3, numberOfUsers);
    }

    @Test
    @DataSet(value="/dbunit/users.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void shouldCreateNewUser() {
        User dummyUser = initDummyUser();
        assertEquals(0, dummyUser.getId());
        assertFalse(userRepositoryUnderTest.existsByEmail(dummyUser.getEmail()));

        User createdUser = userRepositoryUnderTest.create(dummyUser);

        assertNotEquals(0, createdUser.getId());
        assertEquals(dummyUser.getFirstName(), createdUser.getFirstName());
        assertEquals(dummyUser.getLastName(), createdUser.getLastName());
        assertEquals(dummyUser.getEmail(), createdUser.getEmail());
        assertEquals(dummyUser.getAvatar(), createdUser.getAvatar());
        assertEquals(dummyUser.getLocation(), createdUser.getLocation());
        assertEquals(dummyUser.getPosition(), createdUser.getPosition());
        assertEquals(dummyUser.getPhoneNumber(), createdUser.getPhoneNumber());
        assertTrue(userRepositoryUnderTest.existsByEmail(dummyUser.getEmail()));
    }
}
