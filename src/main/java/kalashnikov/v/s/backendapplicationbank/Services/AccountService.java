package kalashnikov.v.s.backendapplicationbank.Services;

import kalashnikov.v.s.backendapplicationbank.DAO.AccountDAO;
import kalashnikov.v.s.backendapplicationbank.DAO.UserDAO;
import kalashnikov.v.s.backendapplicationbank.Entity.Account;
import kalashnikov.v.s.backendapplicationbank.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private UserDAO userDAO;

    public Account openAccount(Account account, Long userId) {
        User user = userDAO.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        account.setUser(user);
        account.setAccountNumber(UUID.randomUUID().toString());
        account.setBalance(0.0);
        return accountDAO.save(account);
    }

    public void closeAccount(Long id) {
        Account account = accountDAO.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
        accountDAO.delete(account);
    }

    public Account getAccount(Long id) {
        return accountDAO.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> getAccountsByUserId(Long userId) {
        return accountDAO.findByUserId(userId);
    }
}
