package kalashnikov.v.s.backendapplicationbank.Services;

import kalashnikov.v.s.backendapplicationbank.DAO.AccountDAO;
import kalashnikov.v.s.backendapplicationbank.DAO.TransactionDAO;
import kalashnikov.v.s.backendapplicationbank.Entity.Account;
import kalashnikov.v.s.backendapplicationbank.Entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private AccountDAO accountDAO;

    public Transaction transferMoney(Long fromAccountId, Long toAccountId, Double amount) {
        Account fromAccount = accountDAO.findById(fromAccountId).orElseThrow(() -> new RuntimeException("From Account not found"));
        Account toAccount = accountDAO.findById(toAccountId).orElseThrow(() -> new RuntimeException("To Account not found"));

        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountDAO.save(fromAccount);
        accountDAO.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setAmount(amount);
        transaction.setAccount(fromAccount);
        transaction.setTargetAccount(toAccount);

        return transactionDAO.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionDAO.findAll();
    }

    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionDAO.findTransactionsByUserId(userId);
    }

    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionDAO.findByAccountId(accountId);
    }
}
