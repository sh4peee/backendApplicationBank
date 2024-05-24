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

    public List<Transaction> findByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }
}
