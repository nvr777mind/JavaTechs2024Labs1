package org.example.Entities.Accounts;

import org.example.Entities.Bank;
import org.example.Entities.User;
import org.example.Exceptions.AccountException;
import org.example.Models.AccountType;
import org.example.Models.Transaction;
import org.example.Models.TransactionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public abstract class Account {

    protected UUID id;
    protected User user;
    protected double money;
    protected AccountType type;
    protected Bank bank;
    protected ArrayList<Transaction> transactions = new ArrayList<>();

    abstract public void topUp(double money) throws AccountException, org.example.Exceptions.AccountException;
    abstract public void withdraw(double money) throws AccountException, org.example.Exceptions.AccountException;
    abstract public void accrualPercent();
    abstract public void transfer(double money, UUID accountId) throws AccountException, org.example.Exceptions.AccountException, javax.security.auth.login.AccountException;

    public double getMoney() {
        return money;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public AccountType getType() {
        return type;
    }

    public void addTransaction(Transaction transaction) {
        if(transaction.getType() == TransactionType.undo) { transaction.cancelTransaction(); }
        transactions.add(transaction);
    }

    public Bank getBank() {
        return bank;
    }

    public Transaction findTransaction(UUID transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getId() == transactionId) {
                return transaction;
            }
        }

        return null;
    }

    public Transaction getLastTransaction(){
        return transactions.getLast();
    }

    public void undo(UUID transactionID) throws AccountException {
        Transaction transaction = findTransaction(transactionID);

        if (transaction.getStatus()) {
            throw AccountException.invalidTransaction();
        }

        if (transaction.getType() == TransactionType.transfer) {
            transaction.getStartAccount().money += transaction.getMoney();
            transaction.getFinishAccount().money -= transaction.getMoney();
            transaction.cancelTransaction();

            addTransaction(new Transaction(TransactionType.undo, transaction.getFinishAccount(), transaction.getStartAccount(), LocalDateTime.now(), transaction.getMoney()));
        }

        if (transaction.getType() == TransactionType.withdraw)
        {
            transaction.getStartAccount().money += transaction.getMoney();
            transaction.cancelTransaction();
            addTransaction(new Transaction(TransactionType.undo, transaction.getStartAccount(), transaction.getStartAccount(), LocalDateTime.now(), transaction.getMoney()));

        }

        if (transaction.getType() == TransactionType.topUp)
        {
            transaction.getStartAccount().money -= transaction.getMoney();
            transaction.cancelTransaction();
            addTransaction(new Transaction(TransactionType.undo, transaction.getStartAccount(), transaction.getStartAccount(), LocalDateTime.now(), transaction.getMoney()));
        }
    }
}
