package com.revature.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {
    public static String hashPassword(String input){
            return BCrypt.hashpw(input, BCrypt.gensalt(14));
    }

    public static boolean isCorrectPassword(String input, String hashed) {
        return BCrypt.checkpw(input, hashed);
    }
}