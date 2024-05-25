package kalashnikov.v.s.backendapplicationbank.Controllers;

import kalashnikov.v.s.backendapplicationbank.Entity.Transaction;
import kalashnikov.v.s.backendapplicationbank.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.findAll();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/transactions/user/{userId}")
    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable Long userId) {
        List<Transaction> userTransactions = transactionService.findByAccountId(userId);
        return ResponseEntity.ok(userTransactions);
    }

    @GetMapping("/transactions/account/{accountId}")
    public ResponseEntity<List<Transaction>> getAccountTransactions(@PathVariable Long accountId) {
        List<Transaction> accountTransactions = transactionService.findByAccountId(accountId);
        return ResponseEntity.ok(accountTransactions);
    }
}
