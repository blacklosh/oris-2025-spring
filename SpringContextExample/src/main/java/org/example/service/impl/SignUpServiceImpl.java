package org.example.service.impl;

import org.example.blacklist.PasswordBlacklistRepository;
import org.example.service.SignUpService;
import org.example.validation.EmailValidator;
import org.example.validation.PasswordValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final PasswordBlacklistRepository passwordBlacklistRepository;

    public SignUpServiceImpl(@Qualifier("emailPatternValidator") EmailValidator emailValidator, PasswordValidator passwordValidator, PasswordBlacklistRepository passwordBlacklistRepository) {
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
        this.passwordBlacklistRepository = passwordBlacklistRepository;
    }

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
