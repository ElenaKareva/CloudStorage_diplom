package com.example.cloudDiplom.service;

import com.example.cloudDiplom.dto.request.LoginRequestDto;
import com.example.cloudDiplom.dto.response.LoginResponseDto;
import com.example.cloudDiplom.repository.AuthenticationRepo;
import com.example.cloudDiplom.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@RequiredArgsConstructor
@Service
@Slf4j
public class AuthenticationService {
    private final AuthenticationRepo authenticationRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    private static final Map<String, String> TOKENS = new ConcurrentHashMap<>();

    public LoginResponseDto login(LoginRequestDto request) {
        final String userName = request.getLogin();
        final String password = request.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        final UserDetails userDetails = userService.loadUserByUserName(userName);
        final String token = jwtTokenUtil.generateToken(userDetails);
        TOKENS.put(token, userName);
        log.info("Пользователь " + userName + " Логин");
        return new LoginResponseDto(token);
    }

    public void logout(String authToken) {
        final String token = authToken.substring(7);
        final String username = authenticationRepo.getUserNameByToken(token);
        log.info("Пользователь {} вышел. JWT отключен.", username);
        authenticationRepo.removeTokenAndUserNameByToken(token);
    }
}
