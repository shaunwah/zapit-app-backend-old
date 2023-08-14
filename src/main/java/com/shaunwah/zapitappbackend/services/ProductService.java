package com.shaunwah.zapitappbackend.services;

import com.shaunwah.zapitappbackend.models.Product;
import com.shaunwah.zapitappbackend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts(int limit, int offset) {
        return productRepository.getProducts(limit, offset);
    }

    public List<Product> getProductsByMerchantId(int merchantId, int limit, int offset) {
        return productRepository.getProductsByMerchantId(merchantId, limit, offset);
    }

    public Optional<Product> getProductById(int id) {
        try {
            return Optional.of(productRepository.getProductById(id));
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    public Boolean createProduct(Product product) {
        return productRepository.createProduct(product);
    }

    public Boolean updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    public Boolean deleteProduct(int id) {
        return productRepository.deleteProduct(id);
    }
}
