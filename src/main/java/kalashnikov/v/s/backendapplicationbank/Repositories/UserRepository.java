package kalashnikov.v.s.backendapplicationbank.Repositories;

import kalashnikov.v.s.backendapplicationbank.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}