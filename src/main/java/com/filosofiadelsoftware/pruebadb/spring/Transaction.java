package com.filosofiadelsoftware.pruebadb.spring;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_account_id")
    private Long sourceAccount;

    @Column(name = "destination_account_id")
    private Long destinationAccount;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

}
