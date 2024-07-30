package org.example.Models;

import org.example.Entities.Accounts.Account;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final TransactionType type;
    private final Account startAccount;
    private final Account finishAccount;
    private final UUID id;
    private final LocalDateTime date;
    private final double money;
    private boolean isCanceled;

    public Transaction(TransactionType type, Account startAccount, Account finishAccount, LocalDateTime date, double money) {
        this.type = type;
        this.startAccount = startAccount;
        this.finishAccount = finishAccount;
        this.id = UUID.randomUUID();
        this.date = date;
        this.money = money;
        this.isCanceled = false;
    }

    public Account getStartAccount() {
        return startAccount;
    }

    public Account getFinishAccount(){
        return finishAccount;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getMoney() {
        return money;
    }

    public TransactionType getType() {
        return type;
    }

    public boolean getStatus(){
        return isCanceled;
    }

    public void cancelTransaction(){
        this.isCanceled = true;
    }
}
