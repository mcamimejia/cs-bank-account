package csBankAccount.repository;

import csBankAccount.entities.BankAccount;
import csBankAccount.entities.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	List<Transaction> findByBankAccount(BankAccount bankAccount); 
}