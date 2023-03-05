package com.example.cloudDiplom.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AuthenticationRepo {

    private final Map<String, String> tokensAndUserNames = new ConcurrentHashMap<>();

    public void putTokenAndUserName(String token, String userName) {
        tokensAndUserNames.put(token, userName);
    }

    public void removeTokenAndUserNameByToken(String token) {
        tokensAndUserNames.remove(token);
    }

    public String getUserNameByToken(String token) {
        return tokensAndUserNames.get(token);
    }
}

