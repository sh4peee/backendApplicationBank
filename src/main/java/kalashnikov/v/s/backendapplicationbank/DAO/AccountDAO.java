package kalashnikov.v.s.backendapplicationbank.DAO;

import kalashnikov.v.s.backendapplicationbank.Entity.Account;
import kalashnikov.v.s.backendapplicationbank.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDAO {
    @Autowired
    private AccountRepository accountRepository;

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> findByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }
}
