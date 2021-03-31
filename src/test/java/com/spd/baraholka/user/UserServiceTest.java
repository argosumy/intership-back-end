package com.spd.baraholka.user;

import com.spd.baraholka.user.controller.mappers.UserAdditionalResourceMapper;
import com.spd.baraholka.user.controller.mappers.UserMapper;
import com.spd.baraholka.user.persistance.PersistenceUserAdditionalResourcesService;
import com.spd.baraholka.user.persistance.PersistenceUserService;
import com.spd.baraholka.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private PersistenceUserService persistenceUserService;

    @Mock
    private PersistenceUserAdditionalResourcesService persistenceUserAdditionalResourcesService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserAdditionalResourceMapper userAdditionalResourceMapper;

    @InjectMocks
    private UserService userServiceUnderTest;

    @BeforeEach
    void init() {
        userServiceUnderTest = new UserService(persistenceUserService,
                persistenceUserAdditionalResourcesService,
                userMapper,
                userAdditionalResourceMapper);
    }

    @Test
    @DisplayName("'Should invoke UserRepository.existsByEmail when UserService.existsByEmail is invoked")
    void shouldInvokeRepoExistsByEmailTest() {
        String dummyEmail = "dummy@email.com";
        userServiceUnderTest.existsByEmail(dummyEmail);
        verify(persistenceUserService, times(1)).existsByEmail(dummyEmail);
    }

    @Test
    void shouldInvokeRepoCountAllTest() {
        userServiceUnderTest.count();
        verify(persistenceUserService, times(1)).count();
    }
}
