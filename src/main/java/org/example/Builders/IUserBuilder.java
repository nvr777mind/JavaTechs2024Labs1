package org.example.Builders;

import org.example.Entities.User;

public interface IUserBuilder extends IBuilder<User> {
    IUserBuilder withName (String firstName, String lastName);
    IUserBuilder withPassportId(Integer passportId);
    IUserBuilder withAddress (String address);
}
