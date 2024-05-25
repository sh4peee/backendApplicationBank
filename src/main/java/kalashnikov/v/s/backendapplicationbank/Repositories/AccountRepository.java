package kalashnikov.v.s.backendapplicationbank.Repositories;

import kalashnikov.v.s.backendapplicationbank.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long userId);
}