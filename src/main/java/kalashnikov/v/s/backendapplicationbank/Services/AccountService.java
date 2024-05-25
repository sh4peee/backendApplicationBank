package kalashnikov.v.s.backendapplicationbank.Services;

import kalashnikov.v.s.backendapplicationbank.DAO.AccountDAO;
import kalashnikov.v.s.backendapplicationbank.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountDAO accountDAO;

    public Account openAccount(Account account) {
        account.setBalance(BigDecimal.ZERO);
        return accountDAO.save(account);
    }

    public Account findById(Long id) {
        return accountDAO.findById(id);
    }

    public List<Account> findByUserId(Long userId) {
        return accountDAO.findByUserId(userId);
    }

    public void closeAccount(Long id) {
        accountDAO.deleteById(id);
    }

    public List<Account> findAll() {
        return accountDAO.findAll();
    }
}
