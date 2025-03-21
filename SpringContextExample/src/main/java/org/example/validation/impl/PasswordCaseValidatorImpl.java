package org.example.validation.impl;

import lombok.RequiredArgsConstructor;
import org.example.validation.PasswordValidator;

import java.util.Objects;

@RequiredArgsConstructor
public class PasswordCaseValidatorImpl implements PasswordValidator {

    private final int lowerCaseMinCount;

    private final int upperCaseMinCount;

    @Override
    public boolean validate(String password) {
        if (Objects.isNull(password) || password.isEmpty() || password.isBlank()) return false;

        int lowerCaseCount = 0;
        int upperCaseCount = 0;

        for (char ch : password.toCharArray()) {
            if (Character.isLowerCase(ch)) lowerCaseCount++;
            if (Character.isUpperCase(ch)) upperCaseCount++;

            if (lowerCaseCount >= lowerCaseMinCount && upperCaseCount >= upperCaseMinCount)
                return true;
        }

        return false;
    }
}