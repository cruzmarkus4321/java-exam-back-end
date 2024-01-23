package com.java.exam.service;

import com.java.exam.model.AuthResponse;
import com.java.exam.model.Employee;
import com.java.exam.model.User;
import com.java.exam.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User register(String username, String password, int userType) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }

        User user = new User();
        user.setUsername(username);
        user.setUserType(userType);

        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public AuthResponse login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty() || !isPasswordValid(userOptional.get().getPassword(), password)) {
            //throw new IllegalArgumentException("Invalid username or password");
            return new AuthResponse("", null, "failed", "Invalid username or password");
        }

        String token = generateJwtToken(username);

        User user = userOptional.get();

        return new AuthResponse(token, user, "success", "Login successful");
    }

    private boolean isPasswordValid(String hashedPassword, String rawPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    private String generateJwtToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
