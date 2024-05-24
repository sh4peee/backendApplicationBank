package kalashnikov.v.s.backendapplicationbank.Repositories;

import kalashnikov.v.s.backendapplicationbank.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
    List<Transaction> findTransactionsByUserId(Long accountId);
}
