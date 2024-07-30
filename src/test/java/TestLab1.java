import org.example.Builders.UserBuilder;
import org.example.Entities.Accounts.CreditAccount;
import org.example.Entities.Bank;
import org.example.Entities.CentralBank;
import org.example.Entities.User;
import org.example.Exceptions.AccountException;
import org.example.Exceptions.BankException;
import org.example.Exceptions.UserException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class TestLab1 {

    public TestLab1() throws BankException, UserException {
    }

    private final CentralBank centralBank = new CentralBank();

    private final Bank sber = centralBank.createBank("Sber", 10);
    private final Bank tinkoff = centralBank.createBank("Tinkoff", 14.5);
    private final Bank vtb = centralBank.createBank("VTB", 0.5);

    private final UserBuilder userBuider = new UserBuilder();
    private final User messi = userBuider
            .withName("Leonel", "Messi")
            .withPassportId(432423)
            .withAddress("Argentina, Buenos Aires")
            .build();
    private final User ronaldo = userBuider
            .withName("Cristiano", "Ronaldo")
            .withPassportId(161616)
            .withAddress("Portugal, Lisboa")
            .build();


    @Test
    public void topUpWithdrawMoney() throws AccountException {
        var accountMessi = sber.createDebitAccount(messi);

        accountMessi.topUp(90000);
        accountMessi.topUp(7000);
        accountMessi.withdraw(33333);

        Assertions.assertEquals(90000+7000-33333, accountMessi.getMoney());
    }

    @Test
    public void accumulationPercent() throws AccountException {
        var accountMessi = tinkoff.createDebitAccount(messi);

        accountMessi.topUp(90000);
        accountMessi.accrualPercent();

        Assertions.assertEquals(90000+90000*14.5/100/12, accountMessi.getMoney());
    }

    @Test
    public void depositAccount() throws AccountException {
        var accountMessi = sber.createDepositAccount(messi, 90000, LocalDate.now().plusMonths(5));

        accountMessi.topUp(90000);
        accountMessi.withdraw(33333);
        accountMessi.accrualPercent();

        Assertions.assertEquals(90000 + (double) (90000 * 10) /100/12*5, accountMessi.getMoney());
    }

    @Test
    public void creditAccount() throws AccountException {
        CreditAccount accountMessi = vtb.createCreditAccount(messi, 500000, 50);

        accountMessi.topUp(90000);
        accountMessi.withdraw(33333);
        accountMessi.accrualPercent();
        accountMessi.getFullCostOfLoan();

        Assertions.assertEquals(500000-33333, accountMessi.getMoney());
        Assertions.assertEquals(750000, accountMessi.getFullCostOfLoan());
    }

    @Test
    public void moneyTransfer() throws AccountException {
        var accountMessi = vtb.createDebitAccount(messi);
        var accountRonaldo = vtb.createDebitAccount(ronaldo);

        accountMessi.topUp(90000);
        accountRonaldo.topUp(70000);
        accountMessi.transfer(10000, accountRonaldo.getId());

        Assertions.assertEquals(accountMessi.getMoney(), accountRonaldo.getMoney());
    }

    @Test
    public void moneyUndo() throws AccountException {
        var accountMessi = vtb.createDebitAccount(messi);
        var accountRonaldo = vtb.createDebitAccount(ronaldo);
        var accountDima = vtb.createDebitAccount(messi);
        var accountRoma = vtb.createDebitAccount(ronaldo);

        accountMessi.topUp(90000);
        accountMessi.undo(accountMessi.getLastTransaction().getId());
        Assertions.assertEquals(accountMessi.getMoney(), 0);

        accountRonaldo.topUp(70000);
        accountRonaldo.withdraw(40000);
        accountRonaldo.undo(accountRonaldo.getLastTransaction().getId());
        Assertions.assertEquals(accountRonaldo.getMoney(), 70000);

        accountDima.topUp(60000);
        accountRoma.topUp(70000);
        accountDima.transfer(10000, accountRoma.getId());
        accountRoma.withdraw(10000);
        accountDima.undo(accountDima.getLastTransaction().getId());
        Assertions.assertEquals(accountDima.getMoney(), accountRoma.getMoney());
    }
}
