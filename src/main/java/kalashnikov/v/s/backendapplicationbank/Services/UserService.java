package kalashnikov.v.s.backendapplicationbank.Services;

import kalashnikov.v.s.backendapplicationbank.DAO.UserDAO;
import kalashnikov.v.s.backendapplicationbank.Entity.User;
import kalashnikov.v.s.backendapplicationbank.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    public String authenticateUser(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken((UserDetails) authentication.getPrincipal());
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
