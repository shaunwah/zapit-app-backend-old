package com.shaunwah.zapitappbackend.invoice;

import com.shaunwah.zapitappbackend.invoice.InvoiceDetailsRepository;
import com.shaunwah.zapitappbackend.invoice.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceDetailsRepository invoiceDetailsRepository;
}
