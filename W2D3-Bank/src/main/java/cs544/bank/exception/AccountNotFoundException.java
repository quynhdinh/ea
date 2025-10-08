package cs544.bank.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(long accountNumber) {
        super("Account with number " + accountNumber + " not found");
    }
}
