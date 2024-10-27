package com.example.demo.presentation.controller;

import com.example.demo.application.dto.UserDto;
import com.example.demo.application.service.UserService;
import com.example.demo.domain.model.User;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * @author Gerardo De Las Cuevas
 */
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "register")
    public ResponseEntity<String> register(@RequestBody UserDto user){
        User newUser = service.createUser(user);
        String token = jwtUtil.generateToken(newUser.getFirstName());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) throws AuthenticationException {
        return ResponseEntity.ok(service.login(email, password));
    }

}
