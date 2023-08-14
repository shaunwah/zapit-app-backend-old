package com.shaunwah.zapitappbackend.controllers;

import com.shaunwah.zapitappbackend.models.Product;
import com.shaunwah.zapitappbackend.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(defaultValue = "25") Integer limit,
                                                     @RequestParam(defaultValue = "0") Integer offset) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProducts(limit, offset));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(product.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }

    @PostMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        if (productService.createProduct(product)) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("created");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @PutMapping(path = "/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer id,
                                                @RequestBody Product product) {
        if (productService.updateProduct(product)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("updated");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

    @DeleteMapping(path = "/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("deleted");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }
}
