package com.filosofiadelsoftware.pruebadb;

import com.filosofiadelsoftware.pruebadb.spring.Transaction;
import com.filosofiadelsoftware.pruebadb.spring.TransactionDirectService;
import com.filosofiadelsoftware.pruebadb.spring.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/transaction")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionDirectService transactionDirectService;

    @PostMapping(path = "/jpa")
    public ResponseEntity<Transaction> createTransactionWithJpa(@RequestBody TransactionDTO request) {
        Transaction transaction = transactionService.createTransaction(
                request.sourceAccount(), request.destinationAccount(), request.amount());
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @PostMapping(path = "/direct")
    public ResponseEntity<Transaction> createTransactionWithoutJpa(@RequestBody TransactionDTO request) throws SQLException {
        Transaction transaction = transactionDirectService.createTransaction(
                request.sourceAccount(), request.destinationAccount(), request.amount());
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}


