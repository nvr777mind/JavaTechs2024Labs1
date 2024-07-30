package org.example.Entities;

import org.example.Entities.Accounts.Account;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;

    private Integer passportId;
    private String address;

    private final UUID id;

    private ArrayList<Account> accounts;

    public User(String firstName, String lastName, Integer passportId, String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportId = passportId;
        this.address = address;
        id = UUID.randomUUID();
        accounts = new ArrayList<>();
    }

    public boolean isVerifed() {
        return passportId != null & !address.isBlank();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPassportId() {
        return passportId;
    }

    public String getAddress() {
        return address;
    }

    public UUID getId() {
        return id;
    }

    public void addNewAccounts(Account account) {
        accounts.add(account);
    }

}
