package com.shaunwah.zapitappbackend.repositories;

import com.shaunwah.zapitappbackend.models.MerchantStore;
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
//    private final JdbcTemplate jdbcTemplate;
//
//    public List<MerchantStore> getMerchantStores(Boolean isActive, int limit, int offset) {
//        final String QUERY = isActive ?
//                "SELECT * FROM merchant_stores WHERE is_active = TRUE AND is_hidden = FALSE LIMIT ? OFFSET ?" :
//                "SELECT * FROM merchant_stores WHERE is_active = FALSE AND is_hidden = FALSE LIMIT ? OFFSET ?";
//        return jdbcTemplate.query(
//                QUERY,
//                BeanPropertyRowMapper.newInstance(MerchantStore.class),
//                limit,
//                offset
//        );
//    }
//
//    public List<MerchantStore> getMerchantStoresByMerchantId(int merchantId, Boolean isActive, int limit, int offset) {
//        final String QUERY = isActive ?
//                "SELECT * FROM merchant_stores WHERE merchant_id = ? AND is_active = TRUE AND is_hidden = FALSE LIMIT ? OFFSET ?" :
//                "SELECT * FROM merchant_stores WHERE merchant_id = ? AND is_active = FALSE AND is_hidden = FALSE LIMIT ? OFFSET ?";
//        return jdbcTemplate.query(
//                QUERY,
//                BeanPropertyRowMapper.newInstance(MerchantStore.class),
//                merchantId,
//                limit,
//                offset
//        );
//    }
//
//    public MerchantStore getMerchantStoreById(int id) {
//        return jdbcTemplate.queryForObject(
//                "SELECT * FROM merchant_stores WHERE id = ? AND is_hidden = FALSE",
//                BeanPropertyRowMapper.newInstance(MerchantStore.class),
//                id
//        );
//    }
//
//    public MerchantStore createMerchantStore(MerchantStore merchantStore) {
//        KeyHolder key = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement("INSERT INTO merchant_stores (merchant_id, identifier, name, address, telephone, is_active) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, merchantStore.getMerchantId());
//            ps.setString(2, merchantStore.getIdentifier());
//            ps.setString(3, merchantStore.getName());
//            ps.setString(4, merchantStore.getAddress());
//            ps.setString(5, merchantStore.getTelephone());
//            ps.setBoolean(6, merchantStore.getIsActive());
//            return ps;
//        }, key);
//        merchantStore.setId(key.getKey().intValue());
//        return merchantStore;
//    }
//
//    public Boolean updateMerchantStore(MerchantStore merchantStore) {
//        return jdbcTemplate.update(
//                "UPDATE merchant_stores SET merchant_id = ?, identifier = ?, name = ?, address = ?, telephone = ?, is_active = ? WHERE id = ? AND is_hidden = FALSE",
//                merchantStore.getMerchantId(),
//                merchantStore.getIdentifier(),
//                merchantStore.getName(),
//                merchantStore.getAddress(),
//                merchantStore.getTelephone(),
//                merchantStore.getIsActive()
//        ) > 0;
//    }
//
//    public Boolean deleteMerchantStore(Integer id) {
//        return jdbcTemplate.update(
//                "UPDATE merchant_stores SET is_hidden = TRUE WHERE id = ? AND is_hidden = FALSE",
//                id
//        ) > 0;
//    }
//
//    public Boolean deleteMerchantStoresByMerchantId(Integer merchantId) {
//        Integer count = jdbcTemplate.queryForObject(
//                "SELECT COUNT(*) FROM merchant_stores WHERE merchant_id = ? AND is_hidden = FALSE",
//                Integer.class,
//                merchantId
//        );
//
//        return jdbcTemplate.update(
//                "UPDATE merchant_stores SET is_hidden = TRUE WHERE id = ? AND is_hidden = FALSE",
//                merchantId
//        ) == count;
//    }
}
