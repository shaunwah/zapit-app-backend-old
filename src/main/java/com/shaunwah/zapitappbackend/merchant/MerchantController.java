package com.shaunwah.zapitappbackend.merchant;

import com.shaunwah.zapitappbackend.misc.QueryDataException;
import com.shaunwah.zapitappbackend.misc.utilities.Utilities;
import com.shaunwah.zapitappbackend.user.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(
        path = "/api",
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
@Log
public class MerchantController {
    private final MerchantService merchantService;
    private final JwtDecoder jwtDecoder;

    @GetMapping("/merchants") // if active
    public ResponseEntity<List<Merchant>> getMerchants(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "50") Integer size,
            @RequestParam(defaultValue = "id") String sortColumn,
            @RequestParam(defaultValue = "1") Integer sortDirection,
            HttpServletRequest request
    ) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Utilities.generateSortByDirection(sortDirection), sortColumn));
        return ResponseEntity.ok(merchantService.getMerchants(USER_ID, pageable));
    }

    @GetMapping("/merchant/{merchantId}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable Long merchantId, HttpServletRequest request) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        return ResponseEntity.ofNullable(merchantService.getMerchantById(USER_ID, merchantId));
    }

    @PostMapping("/merchants")
    public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant, HttpServletRequest request) throws QueryDataException {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        merchant.setCreatedBy(User.newUserWithId(USER_ID));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(merchantService.createMerchant(merchant));
    }

    @PutMapping("/merchants")
    public ResponseEntity<Merchant> updateMerchant(@RequestBody Merchant merchant, HttpServletRequest request) throws QueryDataException {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        merchant.setCreatedBy(User.newUserWithId(USER_ID));
        return ResponseEntity.ok(merchantService.updateMerchant(merchant));
    }

    @DeleteMapping("/merchant/{merchantId}")
    public ResponseEntity<String> deleteMerchant(@PathVariable Long merchantId, HttpServletRequest request) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        if (merchantService.deleteMerchant(USER_ID, merchantId)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Utilities.generateMessage("deleted").toString());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Utilities.generateMessage("error").toString());
    }
}
