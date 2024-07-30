package org.example.Exceptions;

public class UserException extends Exception {
    private UserException(String message){
        super(message);
    }

    public static UserException invalidUser() {
        return new UserException("Invalid user");
    }

    public static UserException invalidName() {
        return new UserException("Invalid name");
    }

    public static UserException invalidPassportId() {
        return new UserException("Invalid passportId");
    }

    public static UserException invalidAddress() {
        return new UserException("Invalid address");
    }
}
