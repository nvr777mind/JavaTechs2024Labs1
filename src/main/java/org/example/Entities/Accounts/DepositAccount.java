package org.example.Entities.Accounts;

import org.example.Entities.Bank;
import org.example.Entities.User;
import org.example.Models.AccountType;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class DepositAccount extends Account {

    private final LocalDate dateStart;
    private final LocalDate dateEnd;

    public DepositAccount(User user, Bank bank, double money, LocalDate endDate) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.bank = bank;
        this.dateStart = LocalDate.now();
        this.money = money;
        this.dateEnd = endDate;
        this.type = AccountType.DepositAccount;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    @Override
    public void withdraw(double money) { System.out.println("Невозможно снять деньги с депозитного счёта."); }

    @Override
    public void topUp(double money) { System.out.println("Невозможно пополнить депозитный счёт."); }

    @Override
    public void accrualPercent() {
        this.money = this.money + (this.money * bank.getPercent()/100/12) * ChronoUnit.MONTHS.between(dateStart, dateEnd);
    }

    @Override
    public void transfer(double money, UUID accountId){
        System.out.println("Нельзя перевести деньги с дебитового счёта на другие.");
    }
}
