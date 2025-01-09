package csBankAccount.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount("12345", "Maria Mejia");
    }

    @Test
    void testDeposit_ValidAmount() {
        // Test para depósitos válidos
        bankAccount.deposit(100.0);
        assertEquals(100.0, bankAccount.getBalance(), "El saldo debe ser 100 después del depósito");
    }

    @Test
    void testDeposit_InvalidAmount() {
        // Test para depósitos con un monto inválido (menor o igual a 0)
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            bankAccount.deposit(-100.0);
        });
        assertEquals("El monto a depositar debe ser positivo.", thrown.getMessage());
    }

    @Test
    void testWithdraw_ValidAmount() {
        // Test para retiros válidos
        bankAccount.deposit(200.0);
        bankAccount.withdraw(100.0);
        assertEquals(100.0, bankAccount.getBalance(), "El saldo debe ser 100 después del retiro");
    }

    @Test
    void testWithdraw_InsufficientFunds() {
        // Test para intentar retirar más de lo disponible
        bankAccount.deposit(100.0);
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            bankAccount.withdraw(200.0);
        });
        assertEquals("Fondos insuficientes.", thrown.getMessage());
    }

    @Test
    void testWithdraw_InvalidAmount() {
        // Test para intentar retirar un monto inválido (menor o igual a 0)
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            bankAccount.withdraw(-50.0);
        });
        assertEquals("El monto a retirar debe ser positivo.", thrown.getMessage());
    }

    @Test
    void testTransactionHistory() {
        // Test para verificar que se agreguen transacciones correctamente
        bankAccount.deposit(200.0);
        bankAccount.withdraw(50.0);

        assertEquals(2, bankAccount.getTransactions().size(), "Debe haber dos transacciones");
        assertEquals(TransactionType.DEPOSIT, bankAccount.getTransactions().get(0).getType());
        assertEquals(TransactionType.WITHDRAWAL, bankAccount.getTransactions().get(1).getType());
    }
}
