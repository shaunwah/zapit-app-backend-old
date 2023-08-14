package com.shaunwah.zapitappbackend.controllers;

import com.shaunwah.zapitappbackend.models.Transaction;
import com.shaunwah.zapitappbackend.models.TransactionDetails;
import com.shaunwah.zapitappbackend.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    // Transaction
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@RequestParam(defaultValue = "25") Integer limit,
                                                             @RequestParam(defaultValue = "0") Integer offset) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transactionService.getTransactions(limit, offset));
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        if (transaction.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(transaction.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }

    @PostMapping(path = "/transactions", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction) {
        if (transactionService.createTransaction(transaction)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("created");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @PutMapping(path = "/transaction/{id}")
    public ResponseEntity<String> updateTransactionStatus(@PathVariable Integer id,
                                                          @RequestParam String status) {
        if (transactionService.updateTransactionStatus(id, status)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("updated");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @DeleteMapping(path = "/transaction/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Integer id) {
        if (transactionService.deleteTransaction(id)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("deleted");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    // TransactionDetails
    @GetMapping("/transaction/{id}/details")
    public ResponseEntity<List<TransactionDetails>> getTransactionDetailsByTransactionId(@PathVariable Integer id,
                                                                                         @RequestParam(defaultValue = "25") Integer limit,
                                                                                         @RequestParam(defaultValue = "0") Integer offset) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(transactionService.getTransactionDetailsByTransactionId(id, limit, offset));
    }

    @GetMapping("/transaction/{id}/details/{id2}")
    public ResponseEntity<TransactionDetails> getTransactionDetailsById(@PathVariable Integer id,
                                                                        @PathVariable Integer id2) {
        Optional<TransactionDetails> transactionDetails = transactionService.getTransactionDetailsById(id2);
        if (transactionDetails.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(transactionDetails.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }

    @PostMapping(path = "/transaction/{id}/details", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createTransaction(@PathVariable Integer id,
                                                    @RequestBody TransactionDetails transactionDetails) {
        transactionDetails.setTransactionId(id);
        if (transactionService.createTransactionDetails(transactionDetails)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("created");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @DeleteMapping(path = "/transaction/{id}/details/{id2}")
    public ResponseEntity<String> deleteTransactionDetails(@PathVariable Integer id,
                                                           @PathVariable Integer id2) {
        if (transactionService.deleteTransactionDetails(id2)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("deleted");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
}
