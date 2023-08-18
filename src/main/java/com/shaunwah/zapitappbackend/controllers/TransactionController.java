package com.shaunwah.zapitappbackend.controllers;

import com.shaunwah.zapitappbackend.models.Transaction;
import com.shaunwah.zapitappbackend.services.TransactionService;
import com.shaunwah.zapitappbackend.utilities.Utilities;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(
        path = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "50") Integer size,
            @RequestParam(defaultValue = "id") String sortColumn,
            @RequestParam(defaultValue = "1") Integer sortDirection
    ) {
        return ResponseEntity.ok(transactionService.getTransactions(PageRequest.of(page, size, Sort.by(Utilities.generateSortByDirection(sortDirection), sortColumn))));
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long transactionId) {
        return ResponseEntity.ofNullable(transactionService.getTransactionById(transactionId));
    }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.createTransaction(transaction));
    }

    @PutMapping("/transactions")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(transactionService.updateTransaction(transaction));
    }

    @DeleteMapping("/transaction/{transactionId}")
    public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionId) {
        if (transactionService.deleteTransaction(transactionId)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Utilities.generateMessage("deleted").toString());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Utilities.generateMessage("error").toString());
    }
}
