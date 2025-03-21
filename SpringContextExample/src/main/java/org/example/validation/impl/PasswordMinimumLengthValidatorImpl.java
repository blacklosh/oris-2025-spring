package org.example.validation.impl;

import org.example.validation.PasswordValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PasswordMinimumLengthValidatorImpl implements PasswordValidator {

    private final int minLength;

    public PasswordMinimumLengthValidatorImpl(@Value("${password.validation.minLength}") int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean validate(String password) {
        if(Objects.isNull(password)) return false;
        return password.length() >= minLength;
    }
}
