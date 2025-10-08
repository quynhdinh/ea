package cs544.bank.service;

import cs544.bank.dao.AccountDaoJpa;
import cs544.bank.domain.Account;
import cs544.bank.domain.Customer;
import cs544.bank.exception.AccountNotFoundException;
import cs544.bank.exception.InsufficientFundsException;
import cs544.bank.jms.IJMSSender;
import cs544.bank.logging.ILogger;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AccountService implements IAccountService {
    @Autowired
    private AccountDaoJpa accountDAO;
    @Autowired
    private ICurrencyConverter currencyConverter;
    @Autowired
    private IJMSSender jmsSender;
    @Autowired
    private ILogger logger;

    public Account createAccount(long accountNumber, String customerName) {
        Account account = new Account(accountNumber);
        Customer customer = new Customer(customerName);
        account.setCustomer(customer);
        accountDAO.save(account);
        logger.log("createAccount with parameters accountNumber= " + accountNumber +
                " , customerName= " + customerName);
        return account;
    }

    public void deposit(long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        account.deposit(amount);
        accountDAO.save(account);
        logger.log("deposit with parameters accountNumber= " + accountNumber +
                " , amount= " + amount);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount +
                    " to account with accountNumber= " + accountNumber);
        }
    }

    public Account getAccount(long accountNumber) {
        Optional<Account> oa = accountDAO.findById(accountNumber);
        Account account = oa.orElseThrow(() ->
            new AccountNotFoundException(accountNumber)
        );
        return account;
    }

    public Collection<Account> getAllAccounts() {
        Collection<Account> accounts = accountDAO.findAll();
        // To use Account as DTO for App.java we need to have related data 
        // loaded before it leaves the transaction / session which gave
        // it the ability to lazy load
        for (Account account : accounts) {
            Hibernate.initialize(account.getCustomer());
            Hibernate.initialize(account.getEntryList());
        }
        return accounts;
    }

    public void withdraw(long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException(accountNumber, amount);
        }
        account.withdraw(amount);
        accountDAO.save(account);
        logger.log("withdraw with parameters accountNumber= " + accountNumber +
                " , amount= " + amount);
    }

    public void depositEuros(long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        account.deposit(amountDollars);
        accountDAO.save(account);
        logger.log("depositEuros with parameters accountNumber= " + accountNumber +
                " , amount= " + amount);
        if (amountDollars > 10000) {
            jmsSender.sendJMSMessage("Deposit of $ " + amount +
                    " to account with accountNumber= " + accountNumber);
        }
    }

    public void withdrawEuros(long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        double amountDollars = currencyConverter.euroToDollars(amount);
        if (account.getBalance() < amountDollars) {
            throw new InsufficientFundsException(accountNumber, amountDollars);
        }
        account.withdraw(amountDollars);
        accountDAO.save(account);
        logger.log("withdrawEuros with parameters accountNumber= " + accountNumber +
                " , amount= " + amount);
    }

    public void transferFunds(long fromAccountNumber, long toAccountNumber,
                              double amount, String description) {
        Account fromAccount = getAccount(fromAccountNumber);
        Account toAccount = getAccount(toAccountNumber);
        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException(fromAccountNumber, amount);
        }
        fromAccount.transferFunds(toAccount, amount, description);
        accountDAO.save(fromAccount);
        accountDAO.save(toAccount);
        logger.log("transferFunds with parameters fromAccountNumber= " + fromAccountNumber +
                " , toAccountNumber= " + toAccountNumber +
                " , amount= " + amount +
                " , description= " + description);
        if (amount > 10000) {
            jmsSender.sendJMSMessage("TransferFunds of $ " + amount +
                    " from account with accountNumber= " + fromAccountNumber +
                    " to account with accountNumber= " + toAccountNumber);
        }
    }
}
