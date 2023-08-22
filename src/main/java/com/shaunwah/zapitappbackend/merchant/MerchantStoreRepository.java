package com.shaunwah.zapitappbackend.merchant;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MerchantStoreRepository extends CrudRepository<MerchantStore, Long> {
    @Query("select ms from MerchantStore ms where ms.isHidden = false")
    List<MerchantStore> findAll(Pageable pageable);
    @Query("select ms from MerchantStore ms where ms.id = :merchantStoreId and ms.isHidden = false")
    MerchantStore findById(long merchantStoreId);
    @Modifying
    @Query("update MerchantStore ms set ms.isHidden = true where ms.id = :merchantStoreId")
    int deleteById(long merchantStoreId);
}
