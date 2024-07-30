package org.example.Builders;

import org.example.Entities.User;
import org.example.Exceptions.UserException;
import org.example.Models.PassportNumber;

public class UserBuilder implements IUserBuilder {
    private String firstName;
    private String lastName;
    private Integer passportId;
    private String address;

    @Override
    public User build() throws UserException {
        if (firstName.isBlank() | lastName.isBlank()) {
            throw UserException.invalidName();
        }
        if (passportId > PassportNumber.getMaxPassportNumber() | passportId < PassportNumber.getMinPassportNumber()) {
            throw UserException.invalidPassportId();
        }
        if (address.isBlank()) {
            throw UserException.invalidAddress();
        }

        return new User (firstName, lastName, passportId, address);
    }

    @Override
    public IUserBuilder withName(String userFirstName, String userLastName) {
        firstName = userFirstName;
        lastName= userLastName;
        return this;
    }

    @Override
    public IUserBuilder withPassportId(Integer userPassportId) {
        passportId = userPassportId;
        return this;
    }

    @Override
    public IUserBuilder withAddress(String userAddress) {
        address = userAddress;
        return this;
    }
}
