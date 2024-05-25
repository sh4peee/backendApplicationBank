package kalashnikov.v.s.backendapplicationbank.DAO;

import kalashnikov.v.s.backendapplicationbank.Entity.Transaction;
import kalashnikov.v.s.backendapplicationbank.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionDAO {
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public List<Transaction> findByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public void deleteById(Long id) {
        transactionRepository.deleteById(id);
    }
}