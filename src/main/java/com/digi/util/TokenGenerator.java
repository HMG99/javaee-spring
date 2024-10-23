package com.digi.util;

import org.apache.commons.lang3.*;

import java.util.Base64;

public class TokenGenerator {
    public static String generateVerifyCode() {
        return RandomStringUtils.random(6, true, true);
    }
    public static String passwordEncoder(String password) {
        return Base64.getUrlEncoder().encodeToString(password.getBytes());
    }
    public static String generateResetToken() {
        return RandomStringUtils.random(8, false, true);
    }

}
