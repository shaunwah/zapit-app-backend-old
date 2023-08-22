package com.shaunwah.zapitappbackend.product;

import com.shaunwah.zapitappbackend.user.User;
import com.shaunwah.zapitappbackend.misc.utilities.Utilities;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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
public class ProductController {
    private final ProductService productService;
    private final JwtDecoder jwtDecoder;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(defaultValue = "%") String like,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "50") Integer size,
            @RequestParam(defaultValue = "id") String sortColumn,
            @RequestParam(defaultValue = "1") Integer sortDirection,
            HttpServletRequest request
    ) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Utilities.generateSortByDirection(sortDirection), sortColumn));
        return ResponseEntity.ok(productService.getProducts(USER_ID, like, pageable));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId, HttpServletRequest request) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        return ResponseEntity.ofNullable(productService.getProductById(productId));
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product, HttpServletRequest request) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        product.setCreatedBy(User.newUserWithId(USER_ID));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(product));
    }

    @PutMapping("/products")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, HttpServletRequest request) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        product.setCreatedBy(User.newUserWithId(USER_ID));
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId, HttpServletRequest request) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
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

    @GetMapping("/product-categories")
    public ResponseEntity<List<ProductCategory>> getProductCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "50") Integer size,
            @RequestParam(defaultValue = "id") String sortColumn,
            @RequestParam(defaultValue = "1") Integer sortDirection,
            HttpServletRequest request
    ) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Utilities.generateSortByDirection(sortDirection), sortColumn));
        return ResponseEntity.ok(productService.getProductCategories(pageable));
    }

    @GetMapping("/product-category/{productCategoryId}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable Long productCategoryId, HttpServletRequest request) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        return ResponseEntity.ofNullable(productService.getProductCategoryById(productCategoryId));
    }

    @PostMapping("/product-categories")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory, HttpServletRequest request) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        productCategory.setCreatedBy(User.newUserWithId(USER_ID));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProductCategory(productCategory));
    }

    @PutMapping("/product-categories")
    public ResponseEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategory, HttpServletRequest request) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.updateProductCategory(productCategory));
    }

    @DeleteMapping("/product-category/{productCategoryId}")
    public ResponseEntity<String> deleteProductCategory(@PathVariable Long productCategoryId, HttpServletRequest request) {
        final long USER_ID = Utilities.getUserIdFromTokenRequest(request, jwtDecoder);
        if (productService.deleteProductCategory(productCategoryId)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Utilities.generateMessage("deleted").toString());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Utilities.generateMessage("error").toString());
    }
}
