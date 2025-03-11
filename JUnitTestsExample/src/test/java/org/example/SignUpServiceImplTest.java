package org.example;

import org.example.blacklist.PasswordBlacklistRepository;
import org.example.service.impl.SignUpServiceImpl;
import org.example.validation.EmailValidator;
import org.example.validation.PasswordValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SignUpServiceImplTest {

    private static SignUpServiceImpl service;

    @BeforeAll
    static void beforeAll() {
        EmailValidator emailValidator = mock(EmailValidator.class);
        doNothing().when(emailValidator).validate(anyString());
        doThrow(new IllegalArgumentException("Email too short!")).when(emailValidator).validate("badEmail");

        PasswordValidator passwordValidator = mock(PasswordValidator.class);
        when(passwordValidator.validate(anyString())).thenReturn(true);
        when(passwordValidator.validate("badPassword")).thenReturn(false);

        PasswordBlacklistRepository repository = mock(PasswordBlacklistRepository.class);
        when(repository.contains(anyString())).thenReturn(false);
        when(repository.contains("blacklistPassword")).thenReturn(true);

        service = new SignUpServiceImpl(emailValidator, passwordValidator, repository);
    }

    @Test
    void testGoodCredentials() {
        assertDoesNotThrow(() -> service.signUp("goodEmail", "goodPassword"));
    }

    @Test
    void testBadEmail() {
        assertThrows(IllegalArgumentException.class, () -> service.signUp("badEmail", "goodPassword"));
    }

    @Test
    void testBadPassword() {
        assertThrows(IllegalArgumentException.class,
                () -> service.signUp("goodEmail", "badPassword"),
                "Password incorrect");
    }

    @Test
    void testBlacklistPassword() {
        assertThrows(IllegalArgumentException.class,
                () -> service.signUp("goodEmail", "blacklistPassword"),
                "Password is known");
    }

    // TODO: проверить комбинации неправильных полей;
    // TODO: проверить на null;
    // TODO: вынести строки в константы.

}
