package cs544.bank.domain;
// Implement Tests: Write tests for the deposit() and withdraw() methods of the Account class. For example, test that:

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class AccountTest {
    @Test
    public void testDeposit() {
        Account account = new Account(12345);
        account.deposit(100.0);
        assertThat(account.getBalance(), equalTo(100.0));
    }

    @Test
    public void testWithdraw() {
        Account account = new Account(12345);
        account.deposit(200.0);
        account.withdraw(50.0);
        assertThat(account.getBalance(), equalTo(150.0));
    }

}
