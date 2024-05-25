package kalashnikov.v.s.backendapplicationbank.Controllers;

import kalashnikov.v.s.backendapplicationbank.Entity.Account;
import kalashnikov.v.s.backendapplicationbank.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/open")
    public ResponseEntity<Account> openAccount(@Valid @RequestBody Account account) {
        Account openedAccount = accountService.openAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(openedAccount);
    }

    @DeleteMapping("/close/{id}")
    public ResponseEntity<Void> closeAccount(@PathVariable Long id) {
        accountService.closeAccount(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account = accountService.findById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Account>> getUserAccounts(@PathVariable Long userId) {
        List<Account> userAccounts = accountService.findByUserId(userId);
        return ResponseEntity.ok(userAccounts);
    }
}