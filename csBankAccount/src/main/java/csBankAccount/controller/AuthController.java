package csBankAccount.controller;

import csBankAccount.entities.User;
import csBankAccount.config.JwtTokenProvider;
import csBankAccount.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already taken");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User existingUser = userService.getUserByUsername(user.getUsername());

        if (existingUser == null || !passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String token = jwtTokenProvider.generateToken(existingUser.getUsername());
        return ResponseEntity.ok("Bearer " + token);
    }
}
