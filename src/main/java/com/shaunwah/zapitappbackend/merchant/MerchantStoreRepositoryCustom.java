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
            return;
        }
        Query query = entityManager.createQuery("delete from MerchantStore ms where ms.merchant.id = :merchantId and not id in :id");
        query.setParameter("merchantId", merchantId)
                .setParameter("id", merchantStoreIds)
                .executeUpdate();
    }
}
