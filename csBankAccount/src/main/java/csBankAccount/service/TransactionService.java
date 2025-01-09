package csBankAccount.service;

import csBankAccount.entities.BankAccount;
import csBankAccount.entities.Transaction;
import csBankAccount.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
	
	@Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
    
    public List<Transaction> getTransactionsByBankAccount(BankAccount bankAccount) {
        return transactionRepository.findByBankAccount(bankAccount);
    }

}
