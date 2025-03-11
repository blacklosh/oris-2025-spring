package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.blacklist.PasswordBlacklistRepository;
import org.example.service.SignUpService;
import org.example.validation.EmailValidator;
import org.example.validation.PasswordValidator;

@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final PasswordBlacklistRepository passwordBlacklistRepository;

    @Override
    public void signUp(String email, String password) {
        emailValidator.validate(email);

        if(!passwordValidator.validate(password)) {
            throw new IllegalArgumentException("Password incorrect");
        }

        if(passwordBlacklistRepository.contains(password)) {
            throw new IllegalArgumentException("Password is known");
        }
    }
}
