package csBankAccount.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import csBankAccount.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Optional<User> findByUsername(String username);
}
