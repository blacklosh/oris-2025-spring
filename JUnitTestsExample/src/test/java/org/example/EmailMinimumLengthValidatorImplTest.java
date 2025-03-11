package org.example;

import org.example.validation.impl.EmailMinimumLengthValidatorImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailMinimumLengthValidatorImplTest {

    private static EmailMinimumLengthValidatorImpl validator;

    @BeforeAll
    static void beforeAll() {
        validator = new EmailMinimumLengthValidatorImpl(5);
    }

    @Test
    void testLongEmail() {
        assertDoesNotThrow(() -> validator.validate("longEmail"));
    }

    @Test
    void testShortPassword() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validate("000"),
                "Email too short!");
    }

    @Test
    void testNullPassword() {
        assertThrows(IllegalArgumentException.class,
                () -> validator.validate(null),
                "Email too short!");
    }

    @Test
    void testMiddlePassword() {
        assertDoesNotThrow(() -> validator.validate("12345"));
    }

}
