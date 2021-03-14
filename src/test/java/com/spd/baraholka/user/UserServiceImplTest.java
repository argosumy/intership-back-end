package com.spd.baraholka.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    void shouldInvokeRepoExistsByEmailTest() {
        String dummyEmail = "dummy@email.com";
        userServiceUnderTest.existsByEmail(dummyEmail);
        verify(userRepository, times(1)).existsByEmail(dummyEmail);
    }
}
