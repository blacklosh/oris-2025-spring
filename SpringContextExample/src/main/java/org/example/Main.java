package org.example;

import org.example.config.SpringProjectConfig;
import org.example.service.SignUpService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringProjectConfig.class);
        SignUpService signUpService = context.getBean(SignUpService.class);
        try {
            signUpService.signUp("email", "PASSsword");
        } catch (Exception e) {
            System.err.println("ERR: " + e.getMessage() );
        }
    }
}