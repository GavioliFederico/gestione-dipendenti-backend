package com.proj.finale.entity;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

    public static String hashPassword(String plainTextPassword) {
        // Genera un salt casuale
        String salt = BCrypt.gensalt();

        // Hasha la password con il salt
        return BCrypt.hashpw(plainTextPassword, salt);
    }

    public static boolean checkPassword(String candidatePassword, String hashedPassword) {
        // Verifica se la password candidata corrisponde alla password hashata
        return BCrypt.checkpw(candidatePassword, hashedPassword);
    }
	
}
