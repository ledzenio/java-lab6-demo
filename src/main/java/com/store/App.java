package com.store;

/**
 * Простое Java-приложение для демонстрации CI/CD
 */
public class App {

    /**
     * Точка входа в приложение
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println(greet(args[0]));
        } else {
            System.out.println(greet("World"));
        }
    }

    /**
     * Бизнес-метод: формирует приветствие
     */
    public static String greet(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Hello, Stranger!";
        }
        return "Hello, " + name + "!";
    }

    /**
     * Дополнительный метод для демонстрации
     */
    public static int add(int a, int b) {
        return a + b;
    }
}