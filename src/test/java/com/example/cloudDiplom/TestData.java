package com.example.cloudDiplom;

import com.example.cloudDiplom.dto.request.LoginRequestDto;
import com.example.cloudDiplom.dto.response.LoginResponseDto;
import com.example.cloudDiplom.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TestData {

    public static final String TOKEN_1 = "Token1";
    public static final String BEARER_TOKEN = "Bearer Token";
    public static final String BEARER_TOKEN_SUBSTRING_7 = BEARER_TOKEN.substring(7);

    public static final String USERNAME_1 = "Username1";
    public static final String PASSWORD_1 = "Password1";
    public static final User USER_1 = new User(USERNAME_1, PASSWORD_1, null);

    public static final LoginResponseDto LOGIN_RESPONSE_DTO = new LoginResponseDto(TOKEN_1);
    public static final LoginRequestDto LOGIN_REQUEST_DTO = new LoginRequestDto(USERNAME_1, PASSWORD_1);

    public static final UsernamePasswordAuthenticationToken USERNAME_PASSWORD_AUTHENTICATION_TOKEN = new UsernamePasswordAuthenticationToken(USERNAME_1, PASSWORD_1);
}
