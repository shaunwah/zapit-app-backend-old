package com.shaunwah.zapitappbackend.services;

import com.shaunwah.zapitappbackend.models.InvoiceStatus;
import com.shaunwah.zapitappbackend.models.PaymentMethod;
import com.shaunwah.zapitappbackend.models.TransactionStatus;
import com.shaunwah.zapitappbackend.repositories.InvoiceStatusRepository;
import com.shaunwah.zapitappbackend.repositories.PaymentMethodRepository;
import com.shaunwah.zapitappbackend.repositories.TransactionStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MiscService {
    private final InvoiceStatusRepository invoiceStatusRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final TransactionStatusRepository transactionStatusRepository;

    public List<InvoiceStatus> getInvoiceStatuses() {
        return invoiceStatusRepository.findAll();
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    public List<TransactionStatus> getTransactionStatuses() {
        return transactionStatusRepository.findAll();
    }
}
