package com.example.demo.application.service;

import com.example.demo.application.dto.UserDto;
import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UserRepository;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Gerardo De Las Cuevas
 */
@Service
public class UserService {

    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserService(UserRepository repository, AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User createUser(UserDto user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        String email = user.getEmail();
        System.out.println(firstName + lastName + password + email);
        User newUser = new User(firstName, lastName, passwordEncoder.encode(password), email);
        repository.save(newUser);
        return repository.save(newUser);
    }

    public String login(String email, String password) throws AuthenticationException {
        System.out.println(email + password);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));
        if (authentication.isAuthenticated()) {
            return jwtUtil.generateToken(email);
        } else {
            throw new AuthenticationException("Invalid credentials") {};
        }
    }

}
