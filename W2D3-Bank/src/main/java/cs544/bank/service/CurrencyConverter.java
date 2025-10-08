package cs544.bank.service;

import org.springframework.stereotype.Service;

@Service
public class CurrencyConverter implements ICurrencyConverter{
    public double euroToDollars (double amount){
		System.out.println("CurrencyConverter: converting "+amount+" dollars to euros " + (1.1757 * amount));
        return 1.1757 * amount;
    }
    
    public double dollarsToEuros (double amount){
		System.out.println("CurrencyConverter: converting "+amount+" euros to dollars " + (0.8505 * amount));
        return 0.8505 * amount;
    }
}
