package org.example.blacklist;

public interface PasswordBlacklistRepository {

    boolean contains(String password);

}
