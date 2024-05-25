package kalashnikov.v.s.backendapplicationbank.Controllers;

import kalashnikov.v.s.backendapplicationbank.Entity.Transaction;
import kalashnikov.v.s.backendapplicationbank.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@NotNull @RequestParam Long fromAccountId,
                                                @NotNull @RequestParam Long toAccountId,
                                                @NotNull @RequestParam BigDecimal amount) {
        Transaction transaction = transactionService.transfer(fromAccountId, toAccountId, amount);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAll();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable Long accountId) {
        List<Transaction> accountTransactions = transactionService.findByAccountId(accountId);
        return ResponseEntity.ok(accountTransactions);
    }
}