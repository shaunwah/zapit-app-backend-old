package com.shaunwah.zapitappbackend.merchant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class MerchantStoreRepositoryCustom {
    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional // TODO
    public void deleteAllByMerchantStoreIds(long merchantId, Set<Long> merchantStoreIds) {
        if (merchantStoreIds.isEmpty()) {
            merchantStoreIds.add(-1L); // TODO
        }
        System.out.println(merchantStoreIds);
        Query query = entityManager.createNativeQuery("DELETE FROM merchant_stores WHERE merchant_id = ? AND NOT id IN ?");
        query.setParameter(1, merchantId)
                .setParameter(2, merchantStoreIds)
                .executeUpdate();
    }
}
