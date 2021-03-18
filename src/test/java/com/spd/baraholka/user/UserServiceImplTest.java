package com.spd.baraholka.user;

import org.junit.jupiter.api.BeforeEach;
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
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceUnderTest;

    @BeforeEach
    void init() {
        userServiceUnderTest = new UserServiceImpl(userRepository);
    }

    @Test
    @DisplayName("'Should invoke UserRepository.existsByEmail when UserService.existsByEmail is invoked")
    void shouldInvokeRepoExistsByEmailTest() {
        String dummyEmail = "dummy@email.com";
        userServiceUnderTest.existsByEmail(dummyEmail);
        verify(userRepository, times(1)).existsByEmail(dummyEmail);
    }
}
