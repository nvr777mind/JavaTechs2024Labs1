package org.example.Entities.Accounts;

import org.example.Entities.Bank;
import org.example.Entities.User;
import org.example.Exceptions.AccountException;
import org.example.Models.AccountType;
import org.example.Models.Transaction;
import org.example.Models.TransactionType;

import java.time.LocalDateTime;
import java.util.UUID;

public class DebitAccount extends Account {

    public DebitAccount(User user, Bank bank) {
        this.id = UUID.randomUUID();
        money = 0;
        this.user = user;
        this.type = AccountType.DebitAccount;
        this.bank = bank;
    }

    @Override
    public void topUp(double money) throws AccountException {
        if (money < 0) {
            throw AccountException.invalidMoney();
        }

        this.money += money;
        addTransaction(new Transaction(TransactionType.topUp, this, this, LocalDateTime.now(), money));
    }

    @Override
    public void withdraw(double money) throws AccountException {
        if (!this.user.isVerifed() | (this.money - money < 0.0) | money <= 0.0) {
            throw AccountException.invalidMoney();
        }

        this.money -= money;
        addTransaction(new Transaction(TransactionType.withdraw, this, this, LocalDateTime.now(), money));
    }

    @Override
    public void accrualPercent() {
        this.money += this.money * bank.getPercent() / 100 / 12;
    }

    @Override
    public void transfer(double transferMoney, UUID accountId) throws AccountException {
        if (this.bank.findAccount(accountId).getType() != AccountType.DebitAccount) {
            throw AccountException.invalidAccount();
        }

        this.bank.findAccount(accountId).money += transferMoney;
        this.money -= transferMoney;
        addTransaction(new Transaction(TransactionType.transfer, this, this.bank.findAccount(accountId), LocalDateTime.now(), transferMoney));
    }
}
