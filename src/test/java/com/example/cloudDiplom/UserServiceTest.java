package com.example.cloudDiplom;

import com.example.cloudDiplom.repository.UsersRepo;
import com.example.cloudDiplom.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.example.cloudDiplom.TestData.USERNAME_1;
import static com.example.cloudDiplom.TestData.USER_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UsersRepo userRepo;

    @BeforeEach
    void setUp() {
        Mockito.when(userRepo.findByUserName(USERNAME_1)).thenReturn(USER_1);
    }

    @Test
    void loadUserByUserName() {
        assertEquals(USER_1, userService.loadUserByUserName(USERNAME_1));
    }

    @Test
    void loadUserByUserNameUnauthorized() {
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUserName("test"));
    }
}
