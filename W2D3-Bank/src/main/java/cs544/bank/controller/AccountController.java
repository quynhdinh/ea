package cs544.bank.controller;

import cs544.bank.domain.Account;
import cs544.bank.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // --- DTOs ---
    public static record CreateAccountRequest(
            @Min(1) long accountNumber,
            @NotBlank String customerName) {}

    public static record AmountRequest(
            @Min(value = 1, message = "Amount must be greater than zero") double amount) {}

    public static record TransferRequest(
            @Min(1) long fromAccountNumber,
            @Min(1) long toAccountNumber,
            @Min(value = 1, message = "Amount must be greater than zero") double amount,
            @NotBlank String description) {}

    // Create account
    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Account account = accountService.createAccount(request.accountNumber(), request.customerName());
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    // Deposit dollars
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable long accountNumber,
                                        @Valid @RequestBody AmountRequest request) {
        accountService.deposit(accountNumber, request.amount());
        return ResponseEntity.ok().build();
    }

    // Deposit euros
    @PostMapping("/{accountNumber}/deposit-eur")
    public ResponseEntity<Void> depositEuros(@PathVariable long accountNumber,
                                             @Valid @RequestBody AmountRequest request) {
        accountService.depositEuros(accountNumber, request.amount());
        return ResponseEntity.ok().build();
    }

    // Withdraw dollars
    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable long accountNumber,
                                         @Valid @RequestBody AmountRequest request) {
        accountService.withdraw(accountNumber, request.amount());
        return ResponseEntity.ok().build();
    }

    // Withdraw euros
    @PostMapping("/{accountNumber}/withdraw-eur")
    public ResponseEntity<Void> withdrawEuros(@PathVariable long accountNumber,
                                              @Valid @RequestBody AmountRequest request) {
        accountService.withdrawEuros(accountNumber, request.amount());
        return ResponseEntity.ok().build();
    }

    // Transfer funds
    @PostMapping("/transfer")
    public ResponseEntity<Void> transferFunds(@Valid @RequestBody TransferRequest request) {
        accountService.transferFunds(
                request.fromAccountNumber(),
                request.toAccountNumber(),
                request.amount(),
                request.description()
        );
        return ResponseEntity.ok().build();
    }

    // Get single account
    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable long accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        return ResponseEntity.ok(account);
    }

    // Get all accounts
    @GetMapping
    public ResponseEntity<Collection<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
}
