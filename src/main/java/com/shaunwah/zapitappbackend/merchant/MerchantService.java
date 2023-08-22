package com.shaunwah.zapitappbackend.merchant;

import com.shaunwah.zapitappbackend.misc.QueryDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository merchantRepository;
    private final MerchantUserRepository merchantUserRepository;
    private final MerchantUserRepositoryCustom merchantUserRepositoryCustom;
    private final MerchantStoreRepository merchantStoreRepository;
    private final MerchantStoreRepositoryCustom merchantStoreRepositoryCustom;

    public List<Merchant> getMerchants(long userId, Pageable pageable) {
        return merchantRepository.findAll(userId, pageable);
    }

    public Merchant getMerchantById(long merchantId, long userId) {
        return merchantRepository.findById(merchantId, userId);
    }

    @Transactional(rollbackFor = QueryDataException.class)
    public Merchant createMerchant(Merchant merchant) throws QueryDataException {
        try {
            List<MerchantUser> merchantUsers = new LinkedList<>(merchant.getMerchantUsers()
                    .stream()
                    .distinct()
                    .toList());

            MerchantUser merchantUser = new MerchantUser();
            merchantUser.setUser(merchant.getCreatedBy());
            merchantUser.setMerchant(merchant);
            merchantUser.setRoles("ROLE_OWNER");
            merchantUsers.add(merchantUser);
            merchantUsers.forEach(mu -> mu.setCreatedBy(merchant.getCreatedBy()));

            List<MerchantStore> merchantStores = merchant.getMerchantStores();
            merchantStores.forEach(ms -> ms.setCreatedBy(merchant.getCreatedBy()));

            Merchant createdMerchant = merchantRepository.save(merchant);
            merchantUserRepositoryCustom.upsertAll(merchantUsers);
            merchantStoreRepository.saveAll(merchantStores);
            return createdMerchant;
        } catch (Exception e) {
            throw new QueryDataException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = QueryDataException.class)
    public Merchant updateMerchant(Merchant merchant) throws QueryDataException {
        try {
            Set<Long> userIds = new HashSet<>();
            List<MerchantUser> merchantUsers = merchant.getMerchantUsers();
            merchantUsers.forEach(mu -> {
                userIds.add(mu.getUser().getId());
                mu.setMerchant(merchant);
                mu.setCreatedBy(merchant.getCreatedBy());
            });

            Set<Long> merchantStoreIds = new HashSet<>();
            List<MerchantStore> merchantStores = merchant.getMerchantStores();
            merchantStores.forEach(ms -> {
                System.out.println(ms.getId());
                merchantStoreIds.add(ms.getId());
                ms.setMerchant(merchant);
                ms.setCreatedBy(merchant.getCreatedBy());
            });

            Merchant updatedMerchant = merchantRepository.save(merchant);
            merchantUserRepositoryCustom.upsertAll(merchantUsers);
            merchantUserRepositoryCustom.deleteAllByUserIds(merchant.getId(), userIds);
            merchantStoreRepository.saveAll(merchantStores);
            merchantStoreRepositoryCustom.deleteAllByMerchantStoreIds(merchant.getId(), merchantStoreIds);
            return updatedMerchant;
        } catch (Exception e) {
            throw new QueryDataException(e.getMessage());
        }
    }

    @Transactional
    public Boolean deleteMerchant(long merchantId, long userId) {
        return merchantRepository.deleteById(merchantId, userId) > 0;
    }

    public List<MerchantStore> getMerchantStores(Pageable pageable) {
        return merchantStoreRepository.findAll(pageable);
    }

    public MerchantStore getMerchantStoreById(long merchantStoreId) {
        return merchantStoreRepository.findById(merchantStoreId);
    }

    public MerchantStore createMerchantStore(MerchantStore merchantStore) {
        return merchantStoreRepository.save(merchantStore);
    }

    public MerchantStore updateMerchantStore(MerchantStore merchantStore) {
        return merchantStoreRepository.save(merchantStore);
    }

    public Boolean deleteMerchantStore(long merchantStoreId) {
        return merchantStoreRepository.deleteById(merchantStoreId) > 0;
    }

//    // MerchantRepository
//    public List<Merchant> getMerchants(Boolean isActive, int limit, int offset) {
//        return merchantRepository.getMerchants(isActive, limit, offset);
//    }
//
//    public List<Merchant> getMerchantsByUserId(int userId, Boolean isActive, int limit, int offset) {
//        return merchantRepository.getMerchantsByUserId(userId, isActive, limit, offset);
//    }
//
//    @Transactional(rollbackFor = QueryDataException.class)
//    public Optional<Merchant> getMerchantById(int id, Boolean isActive) throws QueryDataException {
//        try {
//            Merchant merchant = merchantRepository.getMerchantById(id);
//            merchant.setMerchantStores(merchantStoreRepository.getMerchantStoresByMerchantId(id, isActive, Integer.MAX_VALUE, 0));
//            return Optional.of(merchant);
//        } catch (Exception e) {
//            throw new QueryDataException(ex.getMessage());
//        }
//    }
//
//    public Optional<Merchant> createMerchant(Merchant merchant) {
//        try {
//            return Optional.of(merchantRepository.createMerchant(merchant));
//        } catch (Exception e) {
//            log.severe(ex.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    public Boolean updateMerchant(Merchant merchant) {
//        return merchantRepository.updateMerchant(merchant);
//    }
//
//    @Transactional(rollbackFor = QueryDataException.class)
//    public void deleteMerchant(int id) throws QueryDataException {
//        try {
//            merchantRepository.deleteMerchant(id);
//            merchantStoreRepository.deleteMerchantStoresByMerchantId(id);
//        } catch (Exception e) {
//            throw new QueryDataException(ex.getMessage());
//        }
//    }
//
//    // MerchantStoreRepository
//    public List<MerchantStore> getMerchantStores(Boolean isActive, int limit, int offset) {
//        return merchantStoreRepository.getMerchantStores(isActive, limit, offset);
//    }
//
//    public List<MerchantStore> getMerchantStoresByMerchantId(int merchantId, Boolean isActive, int limit, int offset) {
//        return merchantStoreRepository.getMerchantStoresByMerchantId(merchantId, isActive, limit, offset);
//    }
//
//    public Optional<MerchantStore> getMerchantStoreById(int id) {
//        try {
//            return Optional.of(merchantStoreRepository.getMerchantStoreById(id));
//        } catch (Exception e) {
//            log.severe(ex.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    public Optional<MerchantStore> createMerchantStore(MerchantStore merchantStore) {
//        try {
//            return Optional.of(merchantStoreRepository.createMerchantStore(merchantStore));
//        } catch (Exception e) {
//            log.severe(ex.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    public Boolean updateMerchantStore(MerchantStore merchantStore) {
//        return merchantStoreRepository.updateMerchantStore(merchantStore);
//    }
//
//    public Boolean deleteMerchantStore(int id) {
//        return merchantStoreRepository.deleteMerchantStore(id);
//    }

}
