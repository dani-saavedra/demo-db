package com.filosofiadelsoftware.pruebadb.spring;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionDirectService {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${db.pass}")
    private String pass;
    @Value("${db.user}")
    private String user;

    public Transaction createTransaction(String sourceAccountNumber, String destinationAccountNumber, double amount) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);

            Account sourceAccount = findAccountByNumber(connection, sourceAccountNumber);
            if (sourceAccount == null) {
                throw new RuntimeException("Source account not found");
            }

            Account destinationAccount = findAccountByNumber(connection, destinationAccountNumber);
            if (destinationAccount == null) {
                throw new RuntimeException("Destination account not found");
            }

            if (sourceAccount.getBalance() < amount) {
                throw new RuntimeException("Insufficient balance");
            }

            //updateAccountBalance(connection, sourceAccountNumber, sourceAccount.getBalance() - amount);
            //updateAccountBalance(connection, destinationAccountNumber, destinationAccount.getBalance() + amount);

            Transaction transaction = new Transaction();
            transaction.setSourceAccount(sourceAccount);
            transaction.setDestinationAccount(destinationAccount);
            transaction.setAmount(amount);
            transaction.setTransactionDate(LocalDateTime.now());

            saveTransaction(connection, transaction);
            connection.commit();
            return transaction;
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw new RuntimeException("Transaction failed: " + e.getMessage(), e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }


    private Account findAccountByNumber(Connection connection, String accountNumber) throws SQLException {
        String query = "SELECT * FROM account WHERE account_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account();
                    account.setId(rs.getLong("id"));
                    account.setAccountNumber(rs.getString("account_number"));
                    account.setBalance(rs.getDouble("balance"));
                    return account;
                }
            }
        }
        return null;
    }

    private void updateAccountBalance(Connection connection, String accountNumber, double newBalance) throws SQLException {
        String updateQuery = "UPDATE account SET balance = ? WHERE account_number = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateQuery)) {
            stmt.setDouble(1, newBalance);
            stmt.setString(2, accountNumber);
            stmt.executeUpdate();
        }
    }

    private void saveTransaction(Connection connection, Transaction transaction) throws SQLException {
        String insertQuery = "INSERT INTO transaction (source_account_id, destination_account_id, amount, transaction_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
            stmt.setLong(1, transaction.getSourceAccount().getId());
            stmt.setLong(2, transaction.getDestinationAccount().getId());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setObject(4, transaction.getTransactionDate());
            stmt.executeUpdate();
        }
    }
}

