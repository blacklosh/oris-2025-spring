package org.example.validation.impl;

import org.example.validation.EmailValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EmailMinimumLengthValidatorImpl implements EmailValidator {

    @Value("${email.validation.minLength}")
    private int minLength;

    @Override
    public void validate(String email) {
        if(Objects.isNull(email) || email.length() < minLength) {
            throw new IllegalArgumentException("Email too short!");
        }
    }
}
