package com.shaunwah.zapitappbackend.controllers;

import com.shaunwah.zapitappbackend.models.*;
import com.shaunwah.zapitappbackend.services.MerchantService;
import com.shaunwah.zapitappbackend.services.ProductService;
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
public class MerchantController {
    private final MerchantService merchantService;
    private final ProductService productService;

    @GetMapping("/merchants") // if active
    public ResponseEntity<List<Merchant>> getMerchants(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "50") Integer size,
            @RequestParam(defaultValue = "id") String sortColumn,
            @RequestParam(defaultValue = "1") Integer sortDirection
    ) {
        return ResponseEntity.ok(merchantService.getMerchants(PageRequest.of(page, size, Sort.by(Utilities.generateSortByDirection(sortDirection), sortColumn))));
    }

    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable Long merchantId) {
        return ResponseEntity.ofNullable(merchantService.getMerchantById(merchantId));
    }

    @PostMapping("/merchants")
    public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(merchantService.createMerchant(merchant));
    }

    @PutMapping("/merchants")
    public ResponseEntity<Merchant> updateMerchant(@RequestBody Merchant merchant) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(merchantService.updateMerchant(merchant));
    }

    @DeleteMapping("/merchant/{merchantId}")
    public ResponseEntity<String> deleteMerchant(@PathVariable Long merchantId) {
        if (merchantService.deleteMerchant(merchantId)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Utilities.generateMessage("deleted").toString());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Utilities.generateMessage("error").toString());
    }

    @GetMapping("/merchant/{merchantId}/stores")
    public ResponseEntity<List<MerchantStore>> getMerchantStores(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "50") Integer size,
            @RequestParam(defaultValue = "id") String sortColumn,
            @RequestParam(defaultValue = "1") Integer sortDirection
    ) {
        return ResponseEntity.ok(merchantService.getMerchantStores(PageRequest.of(page, size, Sort.by(Utilities.generateSortByDirection(sortDirection), sortColumn))));
    }

    @GetMapping("/merchant/{merchantId}/store/{merchantStoreId}")
    public ResponseEntity<MerchantStore> getMerchantStoreById(@PathVariable Long merchantStoreId) {
        return ResponseEntity.ofNullable(merchantService.getMerchantStoreById(merchantStoreId));
    }

    @PostMapping("/merchant/{merchantId}/stores")
    public ResponseEntity<MerchantStore> createMerchantStore(@RequestBody MerchantStore merchantStore) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(merchantService.createMerchantStore(merchantStore));
    }

    @PutMapping("/merchant/{merchantId}/stores")
    public ResponseEntity<MerchantStore> updateMerchantStore(@RequestBody MerchantStore merchantStore) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(merchantService.updateMerchantStore(merchantStore));
    }

    @DeleteMapping("/merchant/{merchantId}/store/{merchantStoreId}")
    public ResponseEntity<String> deleteMerchantStore(@PathVariable Long merchantStoreId) {
        if (merchantService.deleteMerchantStore(merchantStoreId)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Utilities.generateMessage("deleted").toString());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Utilities.generateMessage("error").toString());
    }
    
//    @GetMapping("/merchant/{merchantId}/store/{storeId}")
//    public ResponseEntity<MerchantStore> getMerchantStoreById(@PathVariable Integer storeId) {
//        Optional<MerchantStore> merchantStore = merchantService.getMerchantStoreById(storeId);
//        if (merchantStore.isPresent()) {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(merchantStore.get());
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .build();
//    }

//    @GetMapping("/merchant/{merchantId}/stores")
//    public ResponseEntity<List<MerchantStore>> getMerchantStoresByMerchantId(
//            @PathVariable Integer merchantId,
//            @RequestParam(defaultValue = "true") Boolean isActive,
//            @RequestParam(defaultValue = "50") Integer limit,
//            @RequestParam(defaultValue = "0") Integer offset
//    ) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(merchantService.getMerchantStoresByMerchantId(merchantId, isActive, limit, offset));
//    }

//    @GetMapping("/merchant/{merchantId}/PRODUCTS")
//    public ResponseEntity<List<Merchant>> getMerchantsByMerchantId(
//            @PathVariable Integer merchantId,
//            @RequestParam(defaultValue = "25") Integer limit,
//            @RequestParam(defaultValue = "0") Integer offset
//    ) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(JDBCMerchantService.getMerchantsByMerchantId(merchantId, limit, offset));
//    }
}
