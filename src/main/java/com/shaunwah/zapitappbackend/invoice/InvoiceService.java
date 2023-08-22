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
//
//    public List<Invoice> getInvoices(int limit, int offset) {
//        return invoiceRepository.getInvoices(limit, offset);
//    }
//
//    public List<Invoice> getInvoicesByMerchantStoreId(int merchantStoreId, int limit, int offset) {
//        return invoiceRepository.getInvoicesByMerchantStoreId(merchantStoreId, limit, offset);
//    }
//
//    @Transactional(rollbackFor = QueryDataException.class)
//    public Optional<Invoice> getInvoiceById(int id) throws QueryDataException {
//        try {
//            Invoice invoice = invoiceRepository.getInvoiceById(id);
//            invoice.setInvoiceDetails(invoiceDetailsRepository.getInvoiceDetailsByInvoiceId(id, Integer.MAX_VALUE, 0));
//            return Optional.of(invoice);
//        } catch (Exception e) {
//            throw new QueryDataException(ex.getMessage());
//        }
//    }
//
//    @Transactional(rollbackFor = QueryDataException.class)
//    public Optional<Invoice> createInvoice(Invoice invoice) throws QueryDataException {
//        try {
//            invoice = invoiceRepository.createInvoice(invoice);
//            final int invoiceId = invoice.getId();
//            List<InvoiceDetails> invoiceDetails = invoice.getInvoiceDetails();
//            invoiceDetails.forEach(td -> {
//                td.setId(null); // TODO
//                td.setInvoiceId(invoiceId);
//            });
//            invoiceDetailsRepository.createInvoiceDetails(invoiceDetails);
//            return Optional.of(invoice);
//        } catch (Exception e) {
//            throw new QueryDataException(ex.getMessage());
//        }
//    }
//
//    public void updateInvoiceStatus(int id, String status) {
//        if (!invoiceRepository.updateInvoiceStatus(id, status)) {
//            throw new RuntimeException("failed to update Invoice status");
//        }
//    }
//
//    @Transactional(rollbackFor = QueryDataException.class)
//    public void updateInvoiceIsLocked(int id) throws QueryDataException {
//        try {
//            invoiceRepository.updateInvoiceIsLocked(id);
//            invoiceDetailsRepository.updateInvoiceDetailsIsLockedByTransactionId(id);
//        } catch (Exception e) {
//            throw new QueryDataException(ex.getMessage());
//        }
//    }
//
//    @Transactional(rollbackFor = QueryDataException.class)
//    public void deleteInvoice(int id) throws QueryDataException {
//        try {
//            invoiceRepository.deleteInvoice(id);
//            invoiceDetailsRepository.deleteInvoiceDetailsByInvoiceId(id);
//        } catch (Exception e) {
//            throw new QueryDataException(ex.getMessage());
//        }
//    }
//
//    public List<InvoiceDetails> getInvoiceDetails(int limit, int offset) {
//        return invoiceDetailsRepository.getInvoiceDetails(limit, offset);
//    }
//
//    public List<InvoiceDetails> getInvoiceDetailsByInvoiceId(int transactionId, int limit, int offset) {
//        return invoiceDetailsRepository.getInvoiceDetailsByInvoiceId(transactionId, limit, offset);
//    }
//
//    public Optional<InvoiceDetails> getInvoiceDetailsById(int id) {
//        try {
//            return Optional.of(invoiceDetailsRepository.getInvoiceDetailsById(id));
//        } catch (Exception e) {
//            log.severe(ex.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    public Optional<InvoiceDetails> createInvoiceDetails(InvoiceDetails invoiceDetails) {
//        try {
//            return Optional.of(invoiceDetailsRepository.createInvoiceDetails(invoiceDetails));
//        } catch (Exception e) {
//            log.severe(ex.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    public Boolean updateInvoiceDetails(InvoiceDetails invoiceDetails) {
//        return invoiceDetailsRepository.updateInvoiceDetails(invoiceDetails);
//    }
}
