package com.store;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Модульные тесты для приложения
 */
class AppTest {

    @Test
    void testGreet() {
        assertEquals("Hello, World!", App.greet("World"));
        assertEquals("Hello, Jenkins!", App.greet("Jenkins"));
    }

    @Test
    void testGreetWithEmptyName() {
        assertEquals("Hello, Stranger!", App.greet(""));
        assertEquals("Hello, Stranger!", App.greet(null));
    }

    @Test
    void testAdd() {
        assertEquals(5, App.add(2, 3));
        assertEquals(0, App.add(-1, 1));
        assertEquals(10, App.add(5, 5));
    }

    @Test
    void testMain() {
        // Просто проверяем, что метод main не падает
        assertDoesNotThrow(() -> App.main(new String[]{}));
        assertDoesNotThrow(() -> App.main(new String[]{"Student"}));
    }
}