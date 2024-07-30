package org.example.Entities;

import org.example.Entities.Accounts.Account;
import org.example.Entities.Accounts.CreditAccount;
import org.example.Entities.Accounts.DebitAccount;
import org.example.Entities.Accounts.DepositAccount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Bank {
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    private final String name;
    private double percent;

    public Bank(String name, double percent){
        this.name = name;
        users = new ArrayList<>();
        accounts = new ArrayList<>();
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public void removeUser(UUID id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
            }
        }
    }

    public void removeAccount(UUID id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                accounts.remove(account);
            }
        }
    }

    public DebitAccount createDebitAccount(User user) {
        DebitAccount account = new DebitAccount(user, this);

        user.addNewAccounts(account);
        accounts.add(account);

        return account;
    }

    public Account createDepositAccount(User user, double money, LocalDate dateEnd) {
        DepositAccount account = new DepositAccount(user, this, money, dateEnd);

        user.addNewAccounts(account);
        accounts.add(account);

        return account;
    }

    public CreditAccount createCreditAccount(User user, double loanMoney, double loanPercent) {
        CreditAccount account = new CreditAccount(user, this, loanMoney, loanPercent);

        user.addNewAccounts(account);
        accounts.add(account);

        return account;
    }

    public Account findAccount(UUID accountId) {
        for(var account : accounts) {
            if (account.getId() == accountId) {
                return account;
            }
        }
        return null;
    }
}
