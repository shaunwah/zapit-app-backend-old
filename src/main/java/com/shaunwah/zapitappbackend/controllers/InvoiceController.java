package com.shaunwah.zapitappbackend.controllers;

import com.shaunwah.zapitappbackend.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(
        path = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

//    @GetMapping("/invoices")
//    public ResponseEntity<List<Invoice>> getInvoices(
//            @RequestParam(defaultValue = "50") Integer limit,
//            @RequestParam(defaultValue = "0") Integer offset
//    ) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(invoiceService.getInvoices(limit, offset));
//    }
//
//    @GetMapping("/invoice/{invoiceId}")
//    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Integer invoiceId) throws QueryDataException {
//        Optional<Invoice> invoice = invoiceService.getInvoiceById(invoiceId);
//        if (invoice.isPresent()) {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(invoice.get());
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .build();
//    }
//
//    @PostMapping("/invoices")
//    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) throws QueryDataException {
//        Optional<Invoice> createdInvoice = invoiceService.createInvoice(invoice);
//        if (createdInvoice.isPresent()) {
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body(createdInvoice.get());
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .build();
//    }
//
//    @PutMapping("/invoice/{invoiceId}")
//    public ResponseEntity<String> updateInvoice(
//            @PathVariable Integer invoiceId,
//            @RequestParam String type,
//            @RequestParam(required = false) String status
//    ) {
//        if (type.equalsIgnoreCase("status")) {
//            try {
//                invoiceService.updateInvoiceStatus(invoiceId, status);
//                return ResponseEntity.status(HttpStatus.OK)
//                        .body(Utilities.generateMessage("updated Invoice status").toString());
//            } catch (Exception ex) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(Utilities.generateMessage(ex.getMessage()).toString());
//            }
//        }
//
//        if (type.equalsIgnoreCase("lock")) {
//            try {
//                invoiceService.updateInvoiceIsLocked(invoiceId);
//                return ResponseEntity.status(HttpStatus.OK)
//                        .body(Utilities.generateMessage("locked Invoice").toString());
//            } catch (Exception ex) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body(Utilities.generateMessage(ex.getMessage()).toString());
//            }
//        }
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(Utilities.generateMessage("valid types are STATUS and LOCK").toString());
//    }
//
//    @DeleteMapping("/invoice/{invoiceId}")
//    public ResponseEntity<String> deleteInvoice(@PathVariable Integer invoiceId) {
//        try {
//            invoiceService.deleteInvoice(invoiceId);
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(Utilities.generateMessage("deleted").toString());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(Utilities.generateMessage(ex.getMessage()).toString());
//        }
//    }
//
//    @PostMapping("/invoice/{invoiceId}")
//    public ResponseEntity<InvoiceDetails> createInvoiceDetails(
//            @PathVariable Integer invoiceId,
//            @RequestBody InvoiceDetails invoiceDetails
//    ) {
//        invoiceDetails.setInvoiceId(invoiceId);
//        Optional<InvoiceDetails> createdInvoiceDetails = invoiceService.createInvoiceDetails(invoiceDetails);
//        if (createdInvoiceDetails.isPresent()) {
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body(createdInvoiceDetails.get());
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .build();
//    }
//
//    @PutMapping("/invoice/{invoiceId}/details")
//    public ResponseEntity<String> updateInvoiceDetails(@RequestBody InvoiceDetails invoiceDetails) {
//        if (invoiceService.updateInvoiceDetails(invoiceDetails)) {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(Utilities.generateMessage("updated").toString());
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(Utilities.generateMessage("error").toString());
//    }
}
