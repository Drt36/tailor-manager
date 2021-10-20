package com.internship.tailormanager.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PasswordService {

    public String generatePassword() {
        final String UPPER_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String LOWER_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        final String NUMBER = "0123456789";
        final String ALPHA_NUMERIC = UPPER_ALPHABET + LOWER_ALPHABET + NUMBER;

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int length = 10;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHA_NUMERIC.length());
            char randomChar = ALPHA_NUMERIC.charAt(index);
            stringBuilder.append(randomChar);
        }
        String randomString = stringBuilder.toString();
        return randomString;
    }
}
