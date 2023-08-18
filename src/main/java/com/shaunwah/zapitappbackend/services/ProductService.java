package com.shaunwah.zapitappbackend.services;

import com.shaunwah.zapitappbackend.models.Product;
import com.shaunwah.zapitappbackend.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final JwtDecoder jwtDecoder;

    public List<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> getProductsByMerchantId(long merchantId, Pageable pageable) {
        return productRepository.findAllByMerchantId(merchantId, pageable);
    }

    public Product getProductById(long productId) {
        return productRepository.findById(productId);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public Boolean deleteProduct(long productId) {
        return productRepository.deleteById(productId) > 0;
    }

    public Long getProductCount() {
        return productRepository.count();
    }

//    private final JDBCProductRepository JDBCProductRepository;
//
//    public List<Product> getProducts(String sortByColumn, int sortByOrder, int limit, int offset) {
//        System.out.println(sortByOrder);
//        return JDBCProductRepository.getProducts(
//                sortByColumn,
//                (sortByOrder == -1),
//                limit,
//                offset
//        );
//    }
//
//    public List<Product> getProductsByMerchantId(int merchantId, int limit, int offset) {
//        return JDBCProductRepository.getProductsByMerchantId(merchantId, limit, offset);
//    }
//
//    public Optional<Product> getProductById(int id) {
//        try {
//            return Optional.of(JDBCProductRepository.getProductById(id));
//        } catch (Exception ex) {
//            log.severe(ex.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    public Optional<Product> createProduct(Product product) {
//        try {
//            return Optional.of(JDBCProductRepository.createProduct(product));
//        } catch (Exception ex) {
//            log.severe(ex.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    public Boolean updateProduct(Product product) {
//        return JDBCProductRepository.updateProduct(product);
//    }
//
//    public Boolean deleteProduct(int id) {
//        return JDBCProductRepository.deleteProduct(id);
//    }
}
