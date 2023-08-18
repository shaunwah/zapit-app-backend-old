package com.shaunwah.zapitappbackend.services;

import com.shaunwah.zapitappbackend.models.Merchant;
import com.shaunwah.zapitappbackend.models.MerchantStore;
import com.shaunwah.zapitappbackend.repositories.MerchantRepository;
import com.shaunwah.zapitappbackend.repositories.MerchantStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantService {
    private final MerchantRepository merchantRepository;
    private final MerchantStoreRepository merchantStoreRepository;

    public List<Merchant> getMerchants(Pageable pageable) {
        return merchantRepository.findAll(pageable);
    }

    public Merchant getMerchantById(long merchantId) {
        return merchantRepository.findById(merchantId);
    }

    public Merchant createMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    public Merchant updateMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    public Boolean deleteMerchant(long merchantId) {
        return merchantRepository.deleteById(merchantId) > 0;
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
//        } catch (Exception ex) {
//            throw new QueryDataException(ex.getMessage());
//        }
//    }
//
//    public Optional<Merchant> createMerchant(Merchant merchant) {
//        try {
//            return Optional.of(merchantRepository.createMerchant(merchant));
//        } catch (Exception ex) {
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
//        } catch (Exception ex) {
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
//        } catch (Exception ex) {
//            log.severe(ex.getMessage());
//            return Optional.empty();
//        }
//    }
//
//    public Optional<MerchantStore> createMerchantStore(MerchantStore merchantStore) {
//        try {
//            return Optional.of(merchantStoreRepository.createMerchantStore(merchantStore));
//        } catch (Exception ex) {
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
