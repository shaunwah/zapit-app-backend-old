package com.shaunwah.zapitappbackend.services;

import com.shaunwah.zapitappbackend.models.Transaction;
import com.shaunwah.zapitappbackend.models.TransactionDetails;
import com.shaunwah.zapitappbackend.repositories.TransactionDetailsRepository;
import com.shaunwah.zapitappbackend.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionDetailsRepository transactionDetailsRepository;

    // TransactionRepository
    public List<Transaction> getTransactions(int limit, int offset) {
        return transactionRepository.getTransactions(limit, offset);
    }

    public List<Transaction> getTransactionsByUserId(int userId, int limit, int offset) {
        return transactionRepository.getTransactionsByUserId(userId, limit, offset);
    }

    public Optional<Transaction> getTransactionById(int id) {
        try {
            return Optional.of(transactionRepository.getTransactionById(id));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public Boolean createTransaction(Transaction transaction) {
        return transactionRepository.createTransaction(transaction);
    }

    public Boolean updateTransactionStatus(int id, String status) {
        return transactionRepository.updateTransactionStatus(id, status);
    }

    public Boolean deleteTransaction(int id) {
        return transactionRepository.deleteTransaction(id);
    }

    // TransactionDetailsRepository
    public List<TransactionDetails> getTransactionDetails(int limit, int offset) {
        return transactionDetailsRepository.getTransactionDetails(limit, offset);
    }

    public List<TransactionDetails> getTransactionDetailsByTransactionId(int transactionId, int limit, int offset) {
        return transactionDetailsRepository.getTransactionDetailsByTransactionId(transactionId, limit, offset);
    }

    public Optional<TransactionDetails> getTransactionDetailsById(int id) {
        try {
            return Optional.of(transactionDetailsRepository.getTransactionDetailsById(id));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public Boolean createTransactionDetails(TransactionDetails transactionDetails) {
        return transactionDetailsRepository.createTransactionDetails(transactionDetails);
    }

    public Boolean deleteTransactionDetails(int id) {
        return transactionDetailsRepository.deleteTransactionDetails(id);
    }
}
