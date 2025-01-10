package csBankAccount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import csBankAccount.entities.BankAccount;
import csBankAccount.repository.BankAccountRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private BankAccountRepository bankAccountRepository;
    
    private Long accountId;
    
    @BeforeEach
    void setup() {
        bankAccountRepository.deleteAll();

        BankAccount account = new BankAccount("sssaaa", "Test Account");
        BankAccount savedAccount = bankAccountRepository.save(account);
        accountId = savedAccount.getId();
    }

    @Test
    void shouldReturnAccountDetailsWhenAccountExists() throws Exception {
    	mockMvc.perform(get("/api/accounts/" + accountId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(accountId));
    }

    @Test
    void shouldReturnNotFoundWhenAccountDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/accounts/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewAccount() throws Exception {
        BankAccount newAccount = new BankAccount("aaaa", "Test Account");

        mockMvc.perform(post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAccount)))
                .andExpect(status().isOk());
    }
}