package csBankAccount.controller;

import csBankAccount.entities.User;
import csBankAccount.config.JwtTokenProvider;
import csBankAccount.service.UserService;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        User existingUser = userService.getUserByUsername(user.getUsername());

        Map<String, Object> response = new HashMap<>();
        
        if (existingUser == null || !passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
        	response.put("message", "Invalid username or password");
            return ResponseEntity.status(401).body(response);
        }

        String token = jwtTokenProvider.generateToken(existingUser.getUsername());

        response.put("id", existingUser.getId());
        response.put("token", "Bearer " + token);

        return ResponseEntity.ok(response);
    }
}
