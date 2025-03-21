package org.example.validation.impl;

import lombok.RequiredArgsConstructor;
import org.example.validation.EmailValidator;
import org.springframework.core.annotation.Order;

import java.util.Objects;

@RequiredArgsConstructor
public class EmailPatternValidatorImpl implements EmailValidator {

    private final String EMAIL_PATTERN;

    @Override
    public void validate(String email) {
        if (Objects.isNull(email)) {
            throw new IllegalArgumentException("Email is null");
        }
        if (email.isBlank() || email.isEmpty()) {
            throw new IllegalArgumentException("Email is empty");
        }
        if (!email.matches(EMAIL_PATTERN)) {
            throw new IllegalArgumentException("Email doesn't match pattern");
        }
    }
}