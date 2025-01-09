package csBankAccount.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private String accountHolderName;

    @Column()
    private double balance;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
    public BankAccount() {
    	
    }
    
    public BankAccount(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction(amount, TransactionType.DEPOSIT);
        } else {
            throw new IllegalArgumentException("El monto a depositar debe ser positivo.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            addTransaction(amount, TransactionType.WITHDRAWAL);
        } else if (amount > balance) {
            throw new IllegalArgumentException("Fondos insuficientes.");
        } else {
            throw new IllegalArgumentException("El monto a retirar debe ser positivo.");
        }
    }

    private void addTransaction(double amount, TransactionType type) {
        Transaction transaction = new Transaction(type, amount, this);
        transactions.add(transaction);
    }

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}


}

