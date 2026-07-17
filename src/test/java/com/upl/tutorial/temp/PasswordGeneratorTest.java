package com.upl.tutorial.temp;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGeneratorTest {
    @Test
    void generateBCryptHash() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        String rawPassword = "tutorial@123"; // <-- Put the password you want to hash here
        String hashedPassword = encoder.encode(rawPassword);
//$2a$10$X34cI.Y5t0RBsXDNUE1hDeA5Ih.RfKsKUtmadoaDrfEfQhICEEYSe
        System.out.println("\n==================================================");
        System.out.println("RAW PASSWORD:    " + rawPassword);
        System.out.println("BCRYPT HASH:     " + hashedPassword);
        System.out.println("==================================================\n");

        String rawPassword1 = "project@123"; // <-- Put the password you want to hash here
        String hashedPassword1 = encoder.encode(rawPassword1);
        //$2a$10$J7FW1dwrGfMYEPXhwjtvfO4NLoDkJ3Uuk4cDJ76vh7h72uUhX/7o2
        System.out.println("\n==================================================");
        System.out.println("RAW PASSWORD 1:    " + rawPassword1);
        System.out.println("BCRYPT HASH  1:     " + hashedPassword1);
        System.out.println("==================================================\n");
    }
}
