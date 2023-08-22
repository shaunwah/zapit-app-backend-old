package com.shaunwah.zapitappbackend.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {
    @Query("select pc from ProductCategory pc where pc.isHidden = false")
    List<ProductCategory> findAll(Pageable pageable);
    @Query("select pc from ProductCategory pc where pc.merchant.id = :merchantId and pc.isHidden = false")
    List<ProductCategory> findAllByMerchantId(long merchantId, Pageable pageable);
    @Query("select pc from ProductCategory pc where pc.id = :productCategoryId and pc.isHidden = false")
    ProductCategory findById(long productCategoryId);
    @Modifying
    @Query("update ProductCategory pc set pc.isHidden = true where pc.id = :productCategoryId")
    int deleteById(long productCategoryId);
}
