package com.filosofiadelsoftware.pruebadb;

import com.filosofiadelsoftware.pruebadb.spring.Transaction;
import com.filosofiadelsoftware.pruebadb.spring.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO request) {
        Transaction transaction = transactionService.createTransaction(
                request.sourceAccount(), request.destinationAccount(), request.amount());
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}

