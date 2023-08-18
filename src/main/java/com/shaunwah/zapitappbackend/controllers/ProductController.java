package com.shaunwah.zapitappbackend.controllers;

import com.shaunwah.zapitappbackend.models.Product;
import com.shaunwah.zapitappbackend.models.User;
import com.shaunwah.zapitappbackend.services.ProductService;
import com.shaunwah.zapitappbackend.utilities.Utilities;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
public class ProductController {
    private final ProductService productService;
    private final JwtDecoder jwtDecoder;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "50") Integer size,
            @RequestParam(defaultValue = "id") String sortColumn,
            @RequestParam(defaultValue = "1") Integer sortDirection
    ) {
        return ResponseEntity.ok(productService.getProducts(PageRequest.of(page, size, Sort.by(Utilities.generateSortByDirection(sortDirection), sortColumn))));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ofNullable(productService.getProductById(productId));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, HttpServletRequest request) {
        User user = new User();
        user.setId(Long.parseLong(jwtDecoder.decode(Utilities.getTokenFromRequest(request)).getSubject()));
        product.setCreatedBy(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(product));
    }

    @PutMapping("/products")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.updateProduct(product));
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        if (productService.deleteProduct(productId)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Utilities.generateMessage("deleted").toString());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Utilities.generateMessage("error").toString());
    }

    @GetMapping("/product-count")
    public ResponseEntity<Long> getProductCount() {
        return ResponseEntity.ofNullable(productService.getProductCount());
    }
}
