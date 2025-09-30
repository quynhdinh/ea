package cs544.bank.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cs544.bank.logging.ILogger;

@Aspect
@Component
public class LoggingAspect {
    
    @Autowired
    private ILogger logger;
    
    @After("execution(* cs544.bank.service.AccountService.createAccount(..)) && args(accountNumber, customerName)")
    public void logCreateAccount(JoinPoint joinPoint, long accountNumber, String customerName) {
        logger.log("createAccount with parameters accountNumber= " + accountNumber + " , customerName= " + customerName);
    }
    
    @After("execution(* cs544.bank.service.AccountService.deposit(..)) && args(accountNumber, amount)")
    public void logDeposit(JoinPoint joinPoint, long accountNumber, double amount) {
        logger.log("deposit with parameters accountNumber= " + accountNumber + " , amount= " + amount);
    }
    
    @After("execution(* cs544.bank.service.AccountService.withdraw(..)) && args(accountNumber, amount)")
    public void logWithdraw(JoinPoint joinPoint, long accountNumber, double amount) {
        logger.log("withdraw with parameters accountNumber= " + accountNumber + " , amount= " + amount);
    }
    
    @After("execution(* cs544.bank.service.AccountService.depositEuros(..)) && args(accountNumber, amount)")
    public void logDepositEuros(JoinPoint joinPoint, long accountNumber, double amount) {
        logger.log("depositEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
    }
    
    @After("execution(* cs544.bank.service.AccountService.withdrawEuros(..)) && args(accountNumber, amount)")
    public void logWithdrawEuros(JoinPoint joinPoint, long accountNumber, double amount) {
        logger.log("withdrawEuros with parameters accountNumber= " + accountNumber + " , amount= " + amount);
    }
    
    @After("execution(* cs544.bank.service.AccountService.transferFunds(..)) && args(fromAccountNumber, toAccountNumber, amount, description)")
    public void logTransferFunds(JoinPoint joinPoint, long fromAccountNumber, long toAccountNumber, double amount, String description) {
        logger.log("transferFunds with parameters fromAccountNumber= " + fromAccountNumber + " , toAccountNumber= " + toAccountNumber + " , amount= " + amount + " , description= " + description);
    }
}
