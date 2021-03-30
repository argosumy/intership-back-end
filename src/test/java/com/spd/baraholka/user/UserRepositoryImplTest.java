package com.spd.baraholka.user;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.DBUnitExtension;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.persistance.entities.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(DBUnitExtension.class)
@ExtendWith(MockitoExtension.class)
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE, schema = "public")
class UserRepositoryImplTest {

    @Autowired
    private PersistenceUserService persistenceUserService;

    private User initDummyUser() {
        String dummyEmail = "mock@email.com";
        String dummyFirstName = "Mock Given Name";
        String dummyLastName = "Mock Family Name";
        String dummyImageUrl = "Mock Picture URL";
        User dummyUser = new User();
        dummyUser.setFirstName(dummyFirstName);
        dummyUser.setLastName(dummyLastName);
        dummyUser.setEmail(dummyEmail);
        dummyUser.setImageUrl(dummyImageUrl);
        dummyUser.setLocation("");
        dummyUser.setPosition("");
        dummyUser.setPhoneNumber("");
        return dummyUser;
    }

    @Test
    @DisplayName("'Should return 'true' when a user with a certain email exists in a database")
    @DataSet(value = "/dbunit/users.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void shouldReturnTrueWhenUserWithEmailExists() {
        String existingEmail = "existing@email.com";
        Optional<Boolean> exists = persistenceUserService.existsByEmail(existingEmail);
        assertTrue(exists.orElse(false));
    }

    @Test
    @DisplayName("'Should return 'false' when a user with a certain email does not exist in a database")
    @DataSet(value = "/dbunit/users.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void shouldReturnFalseWhenUserWithEmailDoesntExist() {
        String newEmail = "new@email.com";
        Optional<Boolean> exists = persistenceUserService.existsByEmail(newEmail);
        assertFalse(exists.orElse(false));
    }

    @Test
    @DataSet(value = "/dbunit/users.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void shouldReturnNumberOfAllUsers() {
        Optional<Integer> numberOfUsers = persistenceUserService.count();
        assertEquals(3, numberOfUsers.orElse(0));
    }

    @Test
    @DataSet(value = "/dbunit/users.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void shouldCreateNewUser() {
        User dummyUser = initDummyUser();
        assertEquals(0, dummyUser.getId());
        Optional<Boolean> exists = persistenceUserService.existsByEmail(dummyUser.getEmail());
        assertFalse(exists.orElse(false));

        User createdUser = persistenceUserService.create(dummyUser);

        assertNotEquals(0, createdUser.getId());
        assertEquals(dummyUser.getFirstName(), createdUser.getFirstName());
        assertEquals(dummyUser.getLastName(), createdUser.getLastName());
        assertEquals(dummyUser.getEmail(), createdUser.getEmail());
        assertEquals(dummyUser.getImageUrl(), createdUser.getImageUrl());
        assertEquals(dummyUser.getLocation(), createdUser.getLocation());
        assertEquals(dummyUser.getPosition(), createdUser.getPosition());
        assertEquals(dummyUser.getPhoneNumber(), createdUser.getPhoneNumber());
        assertTrue(exists.orElse(false));
    }
}
