package com.filosofiadelsoftware.pruebadb.spring;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Transaction createTransaction(String sourceAccountNumber, String destinationAccountNumber, double amount) {
        Account sourceAccount = accountRepository.findByAccountNumber(sourceAccountNumber)
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account destinationAccount = accountRepository.findByAccountNumber(destinationAccountNumber)
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        if (sourceAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(destinationAccount);

        Transaction transaction = new Transaction();
        transaction.setSourceAccount(sourceAccount.getId());
        transaction.setDestinationAccount(destinationAccount.getId());
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
}

