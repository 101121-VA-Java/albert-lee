package com.revature.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {
    public static String hashPassword(String input){
            return BCrypt.hashpw(input, BCrypt.gensalt(12));
    }

    public static boolean isCorrectPassword(String input, String hashed) {
        return BCrypt.checkpw(input, hashed);
    }
}