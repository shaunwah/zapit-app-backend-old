package com.shaunwah.zapitappbackend.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    public List<Product> getProducts(long createdById, String nameLike, Pageable pageable) {
        return productRepository.findAllByNameLike(createdById, nameLike, pageable);
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

    public List<ProductCategory> getProductCategories(Pageable pageable) {
        return productCategoryRepository.findAll(pageable);
    }
    public List<ProductCategory> getProductCategoriesByMerchantId(long merchantId, Pageable pageable) {
        return productCategoryRepository.findAllByMerchantId(merchantId, pageable);
    }
    public ProductCategory getProductCategoryById(long productCategoryId) {
        return productCategoryRepository.findById(productCategoryId);
    }
    public ProductCategory createProductCategory(ProductCategory product) {
        return productCategoryRepository.save(product);
    }
    public ProductCategory updateProductCategory(ProductCategory product) {
        return productCategoryRepository.save(product);
    }
    public Boolean deleteProductCategory(long productCategoryId) {
        return productCategoryRepository.deleteById(productCategoryId) > 0;
    }
}
