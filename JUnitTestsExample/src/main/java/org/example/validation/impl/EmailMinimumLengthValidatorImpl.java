package org.example.validation.impl;

import lombok.RequiredArgsConstructor;
import org.example.validation.EmailValidator;
import org.example.validation.PasswordValidator;

import java.util.Objects;

@RequiredArgsConstructor
public class EmailMinimumLengthValidatorImpl implements EmailValidator {

    private final int minLength;

    @Override
    public void validate(String email) {
        if(Objects.isNull(email) || email.length() < minLength) {
            throw new IllegalArgumentException("Email too short!");
        }
    }
}
