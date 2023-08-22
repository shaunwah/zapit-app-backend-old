package com.shaunwah.zapitappbackend.misc;

import com.shaunwah.zapitappbackend.invoice.InvoiceStatus;
import com.shaunwah.zapitappbackend.transaction.PaymentMethod;
import com.shaunwah.zapitappbackend.transaction.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(
        path = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class MiscController {
    private final MiscService miscService;

    @GetMapping("/invoice-statuses")
    public ResponseEntity<List<InvoiceStatus>> getInvoiceStatuses() {
        return ResponseEntity.ok(miscService.getInvoiceStatuses());
    }

    @GetMapping({"/invoice-methods", "/transaction-methods"})
    public ResponseEntity<List<PaymentMethod>> getPaymentMethods() {
        return ResponseEntity.ok(miscService.getPaymentMethods());

    }

    @GetMapping("/transaction-statuses")
    public ResponseEntity<List<TransactionStatus>> getTransactionStatuses() {
        return ResponseEntity.ok(miscService.getTransactionStatuses());

    }
}
