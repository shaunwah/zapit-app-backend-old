package com.shaunwah.zapitappbackend.repositories;

import com.shaunwah.zapitappbackend.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Product> getProducts(int limit, int offset) {
        return jdbcTemplate.query(
                "SELECT * FROM products WHERE is_hidden = FALSE LIMIT ? OFFSET ?",
                BeanPropertyRowMapper.newInstance(Product.class),
                limit,
                offset
        );
    }

    public List<Product> getProductsByMerchantId(int merchantId, int limit, int offset) {
        return jdbcTemplate.query(
                "SELECT * FROM products WHERE merchant_id = ? AND is_hidden = FALSE LIMIT ? OFFSET ?",
                BeanPropertyRowMapper.newInstance(Product.class),
                merchantId,
                limit,
                offset
        );
    }

    public Product getProductById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM products WHERE id = ? AND is_hidden = FALSE",
                BeanPropertyRowMapper.newInstance(Product.class),
                id
        );
    }

    public Boolean createProduct(Product product) {
        return jdbcTemplate.update(
                "INSERT INTO products VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                null,
                product.getMerchantId(),
                product.getIdentifier(),
                product.getName(),
                product.getUnitPrice(),
                product.getIsHidden(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        ) > 0;
    }

    public Boolean updateProduct(Product product) {
        return jdbcTemplate.update(
                "UPDATE products SET merchant_id = ?, identifier = ?, name = ?, unit_price = ? WHERE id = ? AND is_hidden = FALSE",
                product.getMerchantId(),
                product.getIdentifier(),
                product.getName(),
                product.getUnitPrice(),
                product.getId()
        ) > 0;
    }

    public Boolean deleteProduct(Integer id) {
        return jdbcTemplate.update(
                "UPDATE products SET is_hidden = TRUE WHERE id = ? AND is_hidden = FALSE",
                id
        ) > 0;
    }
}
