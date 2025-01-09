package csBankAccount.service;

import csBankAccount.entities.BankAccount;
import csBankAccount.entities.Transaction;
import csBankAccount.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount createAccount(BankAccount account) {
        return bankAccountRepository.save(account);
    }

    public Optional<BankAccount> getAccountById(Long id) {
        return bankAccountRepository.findById(id);
    }

    public List<BankAccount> getAllAccounts() {
        return bankAccountRepository.findAll();
    }

    public void deposit(Long accountId, double amount) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        if (amount > 0) {
            account.deposit(amount);
            bankAccountRepository.save(account);
        } else {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
    }

    public void withdraw(Long accountId, double amount) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        if (amount > 0 && amount <= account.getBalance()) {
            account.withdraw(amount);
            bankAccountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Fondos insuficientes o monto inválido");
        }
    }

    public List<Transaction> getTransactionHistory(Long accountId) {
        BankAccount account = bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada"));
        return account.getTransactions();
    }
}