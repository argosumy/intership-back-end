package com.spd.baraholka.user;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.DBUnitExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(DBUnitExtension.class)
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE, schema = "public")
class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepositoryUnderTest;

    @Test
    @DisplayName("'Should return 'true' when a user with a certain email exists in a database")
    @DataSet(value = "/dbunit/users.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void shouldReturnTrueWhenUserWithEmailExists() {
        String existingEmail = "existing@email.com";
        boolean exists = userRepositoryUnderTest.existsByEmail(existingEmail);
        assertTrue(exists);
    }

    @Test
    @DisplayName("'Should return 'false' when a user with a certain email does not exist in a database")
    @DataSet(value = "/dbunit/users.yml", strategy = SeedStrategy.CLEAN_INSERT)
    void shouldReturnFalseWhenUserWithEmailDoesntExist() {
        String newEmail = "new@email.com";
        boolean exists = userRepositoryUnderTest.existsByEmail(newEmail);
        assertFalse(exists);
    }
}
