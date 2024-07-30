package org.example.Exceptions;

public class BankException extends Exception {
    private BankException(String message) {
        super(message);
    }

    public static BankException invalidBank() {
        return new BankException("Invalid bank");
    }
}
