package org.example.Entities;

import org.example.Exceptions.BankException;

import java.util.ArrayList;
import java.util.Objects;

public class CentralBank {
    private ArrayList<Bank> banks;

    public CentralBank() {
        this.banks = new ArrayList<>();
    }

    public void removeBank(String bankName) {
        banks.remove(findBank(bankName));
    }

    public Bank createBank(String name, double percent) throws BankException {
        if (name.isBlank() | percent <= 0) {
            throw BankException.invalidBank();
        }

        Bank bank = new Bank(name, percent);
        banks.add(bank);

        return bank;
    }

    public Bank findBank(String bankName) {
        for (var bank : banks) {
            if (Objects.equals(bank.getName(), bankName)) {
                return bank;
            }
        }

        return null;
    }
}
