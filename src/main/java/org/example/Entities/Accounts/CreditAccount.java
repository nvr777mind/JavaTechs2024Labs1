package org.example.Entities.Accounts;

import org.example.Entities.Bank;
import org.example.Entities.User;
import org.example.Models.AccountType;
import org.example.Models.Transaction;
import org.example.Models.TransactionType;
import org.example.Exceptions.AccountException;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreditAccount extends Account {
    private final double loanPercent;
    private final double loanMoney;

    public CreditAccount(User user, Bank bank, double money, double loanInterest) {
        this.id = UUID.randomUUID();
        this.type = AccountType.CreditAccount;
        this.money = money;
        this.user = user;
        this.bank = bank;
        this.loanPercent = loanInterest;
        this.loanMoney = money;
    }

    public double getLoanInterest() {
        return this.loanPercent;
    }

    public double getFullCostOfLoan() {
        return loanMoney + loanMoney * loanPercent / 100;
    }

    @Override
    public void topUp(double money) {
        System.out.println("Невозможно пополнить кредитный счёт.");
    }

    @Override
    public void withdraw(double money) throws AccountException {
        if (!user.isVerifed() | this.money < money | money <= 0) {
            throw AccountException.invalidMoney();
        }

        this.money -= money;
        addTransaction(new Transaction(TransactionType.withdraw, this, this, LocalDateTime.now(), money));
    }

    @Override
    public void accrualPercent() {
        System.out.println("Проценты не начисляются на кредитный счёт");
    }

    @Override
    public void transfer(double money, UUID accountId){
        System.out.println("Нельзя перевести деньги с кредитного счёта на другие.");
    }
}
