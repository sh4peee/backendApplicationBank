package kalashnikov.v.s.backendapplicationbank.Services;

import kalashnikov.v.s.backendapplicationbank.DAO.AccountDAO;
import kalashnikov.v.s.backendapplicationbank.DAO.TransactionDAO;
import kalashnikov.v.s.backendapplicationbank.Entity.Account;
import kalashnikov.v.s.backendapplicationbank.Entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private AccountDAO accountDAO;

    public Transaction transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = accountDAO.findById(fromAccountId);
        Account toAccount = accountDAO.findById(toAccountId);

        if (fromAccount == null || toAccount == null || fromAccount.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Invalid transaction");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        accountDAO.save(fromAccount);
        accountDAO.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setAccount(fromAccount);
        transaction.setAmount(amount.negate());
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription("Transfer to account " + toAccountId);
        transactionDAO.save(transaction);

        transaction = new Transaction();
        transaction.setAccount(toAccount);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setDescription("Transfer from account " + fromAccountId);
        return transactionDAO.save(transaction);
    }

    public List<Transaction> findAll() {
        return transactionDAO.findAll();
    }

    public List<Transaction> findByAccountId(Long accountId) {
        return transactionDAO.findByAccountId(accountId);
    }
}