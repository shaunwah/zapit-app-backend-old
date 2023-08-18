package com.shaunwah.zapitappbackend.services;

import com.shaunwah.zapitappbackend.models.Transaction;
import com.shaunwah.zapitappbackend.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> getTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    public Transaction getTransactionById(long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction updateTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Boolean deleteTransaction(long transactionId) {
        return transactionRepository.deleteById(transactionId) > 0;
    }

//    public List<Transaction> getTransactions(int limit, int offset) {
//        return transactionRepository.getTransactions(limit, offset);
//    }
//
//    public List<Transaction> getTransactionsByUserId(int userId, int limit, int offset) {
//        return transactionRepository.getTransactionsByUserId(userId, limit, offset);
//    }
//
//    public Optional<Transaction> getTransactionById(int id) {
//        try {
//            return Optional.of(transactionRepository.getTransactionById(id));
//        } catch (Exception ex) {
//            log.severe(ex.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    public Optional<Transaction> createTransaction(Transaction transaction) {
//        try {
//            return Optional.of(transactionRepository.createTransaction(transaction));
//        } catch (Exception ex) {
//            log.severe(ex.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    public Boolean updateTransaction(Transaction transaction) {
//        return transactionRepository.updateTransaction(transaction);
//    }
//
//    public Boolean deleteTransaction(int id) {
//        return transactionRepository.deleteTransaction(id);
//    }
}
