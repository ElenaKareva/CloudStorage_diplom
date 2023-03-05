package com.example.cloudDiplom;

import com.example.cloudDiplom.repository.AuthenticationRepo;
import com.example.cloudDiplom.security.JwtTokenUtil;
import com.example.cloudDiplom.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.AuthenticationManager;

import static com.example.cloudDiplom.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private AuthenticationRepo authenticationRepo;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    /*@Mock
    private UserService userService;

    @Test
    void login() {
        Mockito.when(userService.loadUserByUserName(USERNAME_1)).thenReturn(USER_1);
        Mockito.when(jwtTokenUtil.generateToken(USER_1)).thenReturn(TOKEN_1);
        assertEquals(LOGIN_RESPONSE_DTO, authenticationService.login(LOGIN_REQUEST_DTO));
        Mockito.verify(authenticationManager, Mockito.times(1)).authenticate(USERNAME_PASSWORD_AUTHENTICATION_TOKEN);
    }
*/
    @Test
    void logout() {
        Mockito.when(authenticationRepo.getUserNameByToken(BEARER_TOKEN_SUBSTRING_7)).thenReturn(USERNAME_1);
        authenticationService.logout(BEARER_TOKEN);
        Mockito.verify(authenticationRepo, Mockito.times(1)).getUserNameByToken(BEARER_TOKEN_SUBSTRING_7);
        Mockito.verify(authenticationRepo, Mockito.times(1)).removeTokenAndUserNameByToken(BEARER_TOKEN_SUBSTRING_7);
    }
}
