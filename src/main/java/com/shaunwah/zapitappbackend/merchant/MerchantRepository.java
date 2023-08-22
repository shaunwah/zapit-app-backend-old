package com.shaunwah.zapitappbackend.merchant;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
    // ENDPOINTS_SECURED
    @Query("select m, mu from Merchant m join MerchantUser mu on m.id = mu.merchant.id where mu.user.id = :userId and m.isHidden = false")
    List<Merchant> findAll(long userId, Pageable pageable);
    @Query("select m, mu from Merchant m join MerchantUser mu on m.id = mu.merchant.id where mu.user.id = :userId and m.id = :merchantId and m.isHidden = false")
    Merchant findById(long userId, long merchantId);
    @Modifying
    @Query("update Merchant m set m.isHidden = true where (select count(mu) from MerchantUser mu where mu.user.id = :userId and mu.merchant.id = :merchantId and mu.roles = 'ROLE_OWNER' and mu.isHidden = false) = 1 and m.isHidden = false")
    Integer deleteById(long userId, long merchantId);
}
