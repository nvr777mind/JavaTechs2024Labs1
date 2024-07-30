package org.example.Exceptions;

public class AccountException extends Exception {
    private AccountException(String message) {
        super(message);
    }

    public static AccountException invalidAccount() {
        return new AccountException("Invalid account");
    }

    public static AccountException invalidMoney() {
        return new AccountException("Invalid money");
    }

    public static AccountException invalidTransaction() {
        return new AccountException("Invalid transaction");
    }
}
