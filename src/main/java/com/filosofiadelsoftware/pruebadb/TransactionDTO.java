package com.filosofiadelsoftware.pruebadb;

public record TransactionDTO(String sourceAccount, String destinationAccount, double amount) {
}
