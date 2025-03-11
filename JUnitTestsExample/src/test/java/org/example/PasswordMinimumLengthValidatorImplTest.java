package org.example;

import org.example.validation.impl.PasswordMinimumLengthValidatorImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordMinimumLengthValidatorImplTest {

    private static PasswordMinimumLengthValidatorImpl validator;

    @BeforeAll
    static void beforeAll() {
        validator = new PasswordMinimumLengthValidatorImpl(5);
    }

    @Test
    void testLongPassword() {
        boolean result = validator.validate("longPassword");
        assertTrue(result);
    }

    @Test
    void testShortPassword() {
        boolean result = validator.validate("000");
        assertFalse(result);
    }

    @Test
    void testNullPassword() {
        boolean result = validator.validate(null);
        assertFalse(result);
    }

    @Test
    void testMiddlePassword() {
        boolean result = validator.validate("12345");
        assertTrue(result);
    }

}
