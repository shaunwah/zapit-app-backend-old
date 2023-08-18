package com.shaunwah.zapitappbackend.repositories;

import com.shaunwah.zapitappbackend.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query("select p from Product p where p.isHidden = false")
    List<Product> findAll(Pageable pageable);
    @Query("select p from Product p where p.merchant = :merchantId and p.isHidden = false")
    List<Product> findAllByMerchantId(long merchantId, Pageable pageable);
    @Query("select p from Product p where p.id = :productId and p.isHidden = false")
    Product findById(long productId);
    @Modifying
    @Query("update Product p set p.isHidden = true where p.id = :productId")
    int deleteById(long productId);

    @Query("select count(p) from Product p where p.isHidden = false")
    long count();

    //    private final JdbcTemplate jdbcTemplate;
//
//    public List<Product> getProducts(String sortByColumn, boolean sortByOrder, int limit, int offset) {
//        System.out.println(sortByColumn);
//        final String QUERY_ASC = "SELECT * FROM products WHERE is_hidden = FALSE ORDER BY ? ASC LIMIT ? OFFSET ?";
//        final String QUERY_DESC = "SELECT * FROM products WHERE is_hidden = FALSE ORDER BY ? DESC LIMIT ? OFFSET ?";
//        String query = !sortByOrder ? QUERY_ASC : QUERY_DESC;
//        return jdbcTemplate.query(
//                query,
//                BeanPropertyRowMapper.newInstance(Product.class),
//                sortByColumn,
//                limit,
//                offset
//        );
//    }
//
//    public List<Product> getProductsByMerchantId(int merchantId, int limit, int offset) {
//        return jdbcTemplate.query(
//                "SELECT * FROM products WHERE merchant_id = ? AND is_hidden = FALSE LIMIT ? OFFSET ?",
//                BeanPropertyRowMapper.newInstance(Product.class),
//                merchantId,
//                limit,
//                offset
//        );
//    }
//
//    public Product getProductById(int id) {
//        return jdbcTemplate.queryForObject(
//                "SELECT * FROM products WHERE id = ? AND is_hidden = FALSE",
//                BeanPropertyRowMapper.newInstance(Product.class),
//                id
//        );
//    }
//
//    public Product createProduct(Product product) {
//        KeyHolder key = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement("INSERT INTO products (merchant_id, identifier, name, unit_price) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//            ps.setLong(1, product.getMerchantId());
//            ps.setString(2, product.getIdentifier());
//            ps.setString(3, product.getName());
//            ps.setDouble(4, product.getUnitPrice());
//            return ps;
//        }, key);
//        product.setId(key.getKey().longValue());
//        return product;
//    }
//
//    public Boolean updateProduct(Product product) {
//        return jdbcTemplate.update(
//                "UPDATE products SET merchant_id = ?, identifier = ?, name = ?, unit_price = ? WHERE id = ? AND is_hidden = FALSE", // TODO userid
//                product.getMerchantId(),
//                product.getIdentifier(),
//                product.getName(),
//                product.getUnitPrice(),
//                product.getId()
//        ) > 0;
//    }
//
//    public Boolean deleteProduct(Integer id) {
//        return jdbcTemplate.update(
//                "UPDATE products SET is_hidden = TRUE WHERE id = ? AND is_hidden = FALSE",
//                id
//        ) > 0;
//    }
}
