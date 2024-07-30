package org.example.Models;

public class PassportNumber {
    private static final int MIN_PASSPORT_NUMBER = 100000;
    private static final int MAX_PASSPORT_NUMBER = 999999;

    public static int getMaxPassportNumber() {
        return MAX_PASSPORT_NUMBER;
    }

    public static int getMinPassportNumber() {
        return MIN_PASSPORT_NUMBER;
    }
}
