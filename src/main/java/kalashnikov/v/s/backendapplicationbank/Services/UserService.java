package kalashnikov.v.s.backendapplicationbank.Services;

import kalashnikov.v.s.backendapplicationbank.DAO.UserDAO;
import kalashnikov.v.s.backendapplicationbank.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    public User authenticateUser(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        throw new RuntimeException("Invalid email or password");
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public User findById(Long id) {
        return userDAO.findById(id);
    }

    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }
}