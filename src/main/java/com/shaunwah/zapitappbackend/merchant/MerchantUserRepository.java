package com.shaunwah.zapitappbackend.merchant;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MerchantUserRepository extends CrudRepository<MerchantUser, Long> {
    @Query("select mu from MerchantUser mu where mu.createdBy.id = :createdById and mu.isHidden = false")
    List<MerchantUser> findAll(long createdById, Pageable pageable);
    @Query("select mu from MerchantUser mu where mu.id = :merchantUserId and mu.isHidden = false")
    MerchantUser findById(long merchantUserId);

    @Query("select mu from MerchantUser mu where mu.merchant.id = :merchantId and mu.isHidden = false")
    List<MerchantUser> findAllByMerchantId(long merchantId);

    @Modifying
    @Query("update MerchantUser mu set mu.isHidden = true where mu.id = :merchantUserId")
    int deleteById(long merchantUserId);

    @Query("select count(mu) from MerchantUser mu where mu.isHidden = false")
    long count();
}
