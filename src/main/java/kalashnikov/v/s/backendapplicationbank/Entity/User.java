package kalashnikov.v.s.backendapplicationbank.Entity;

import jakarta.persistence.*;

import lombok.Data;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;

    @OneToMany(mappedBy = "user")
    private Set<Account> accounts;
}