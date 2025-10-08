package cs544.bank.exception;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(long accountNumber, double amount) {
        super("Insufficient funds in account " + accountNumber + " for withdrawal/transfer of " + amount);
    }
}
