package kalashnikov.v.s.backendapplicationbank.Services;

import kalashnikov.v.s.backendapplicationbank.DAO.UserDAO;
import kalashnikov.v.s.backendapplicationbank.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {
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

    public void deleteUser(Long id) {
        User user = userDAO.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userDAO.delete(user);
    }
}
