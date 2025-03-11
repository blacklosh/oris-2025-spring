package org.example.blacklist.impl;

import org.example.blacklist.PasswordBlacklistRepository;

import java.util.Arrays;
import java.util.List;

public class PasswordBlacklistInMemoryRepositoryImpl implements PasswordBlacklistRepository {

    private final List<String> BAD_PASSWORDS = Arrays.asList("qwerty007", "123", "bad");

    @Override
    public boolean contains(String password) {
        return BAD_PASSWORDS.contains(password);
    }
}
