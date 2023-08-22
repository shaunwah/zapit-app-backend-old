package com.shaunwah.zapitappbackend.merchant;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class MerchantUserRepositoryCustom {
    @PersistenceContext
    private final EntityManager entityManager;

    @Transactional // TODO
    public void upsertAll(List<MerchantUser> merchantUsers) {
        Query query = entityManager.createNativeQuery("insert ignore into merchant_users (merchant_id, user_id, roles, created_by) values (?, ?, ?, ?)");
        merchantUsers.forEach(merchantUser -> {
            query.setParameter(1, merchantUser.getMerchant().getId())
                    .setParameter(2, merchantUser.getUser().getId())
                    .setParameter(3, merchantUser.getRoles())
                    .setParameter(4, merchantUser.getCreatedBy().getId())
                    .executeUpdate();
        });
    }

    @Transactional // TODO
    public void deleteAllByUserIds(long merchantId, Set<Long> userIds) {
        if (userIds.isEmpty()) {
            return;
        }
        Query query = entityManager.createQuery("delete from MerchantUser mu where mu.merchant.id = :merchantId and not mu.user.id in :userIds");
        query.setParameter("merchantId", merchantId)
                .setParameter("userIds", userIds)
                .executeUpdate();
    }
}
