package com.example.cloudDiplom.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class Encoder {


    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String qweasdzxc = encoder.encode("qweasdzxc");
        System.out.println(qweasdzxc);
    }
}