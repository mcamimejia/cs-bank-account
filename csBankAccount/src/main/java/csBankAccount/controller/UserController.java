package csBankAccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import csBankAccount.entities.User;
import csBankAccount.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
    private UserService userService;

	
	@PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

	@GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
    	List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
        		.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
    	User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
}
