package csBankAccount.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "bank_account_id", nullable = false)
    private BankAccount bankAccount;
    
    public Transaction() {
    	
    }
    
    public Transaction(TransactionType type, double amount, BankAccount bankAccount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.bankAccount = bankAccount;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "\nTransacción ID: " + id +
                "\nTipo: " + type +
                "\nMonto: $" + amount +
                "\nFecha: " + timestamp +
                "\n----------------------------------";
    }
}