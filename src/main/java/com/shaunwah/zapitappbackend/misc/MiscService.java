package com.shaunwah.zapitappbackend.misc;

import com.shaunwah.zapitappbackend.invoice.InvoiceStatus;
import com.shaunwah.zapitappbackend.transaction.PaymentMethod;
import com.shaunwah.zapitappbackend.transaction.TransactionStatus;
import com.shaunwah.zapitappbackend.invoice.InvoiceStatusRepository;
import com.shaunwah.zapitappbackend.transaction.PaymentMethodRepository;
import com.shaunwah.zapitappbackend.transaction.TransactionStatusRepository;
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
