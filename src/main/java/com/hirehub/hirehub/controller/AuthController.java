package com.hirehub.hirehub.controller;

import com.hirehub.hirehub.model.User;
import com.hirehub.hirehub.repository.UserRepository;
import com.hirehub.hirehub.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered!");
        }
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Find user by email
        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElse(null);
        if (existingUser == null) {
            return ResponseEntity.badRequest().body("Email not found!");
        }
        // Check password
        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.badRequest().body("Wrong password!");
        }
        // Generate JWT token
        String token = jwtUtil.generateToken(
            existingUser.getEmail(),
            existingUser.getRole().toString()
        );
        return ResponseEntity.ok("Bearer " + token);
    }

}