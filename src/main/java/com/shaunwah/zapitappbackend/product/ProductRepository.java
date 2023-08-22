package com.shaunwah.zapitappbackend.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query("select p from Product p where p.name like %:name% and p.createdBy.id = :createdById and p.isHidden = false")
    List<Product> findAllByNameLike(long createdById, String name, Pageable pageable);
    @Query("select p from Product p where p.merchant = :merchantId and p.isHidden = false")
    List<Product> findAllByMerchantId(long merchantId, Pageable pageable);
    @Query("select p from Product p where p.id = :productId and p.isHidden = false")
    Product findById(long productId);

    @Query("update Product p set p.identifier = :product")
    Product update(Product product);
    @Modifying
    @Query("update Product p set p.isHidden = true where p.id = :productId")
    int deleteById(long productId);
    @Query("select count(p) from Product p where p.isHidden = false")
    long count();
}
