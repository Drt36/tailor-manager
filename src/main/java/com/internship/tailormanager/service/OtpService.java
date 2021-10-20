package com.internship.tailormanager.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {
    public String generateOtpCode() {
        int length = 6;
        final String NUMBER = "0123456789";

        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(NUMBER.length());
            char randomChar = NUMBER.charAt(index);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
}
