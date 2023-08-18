package com.shaunwah.zapitappbackend.repositories;

import com.shaunwah.zapitappbackend.models.Merchant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
    @Query("select m from Merchant m where m.isHidden = false")
    List<Merchant> findAll(Pageable pageable);
    @Query("select m from Merchant m where m.id = :merchantId and m.isHidden = false")
    Merchant findById(long merchantId);
    @Modifying
    @Query("update Merchant m set m.isHidden = true where m.id = :merchantId")
    int deleteById(long merchantId);
//    private final JdbcTemplate jdbcTemplate;
//
//    public List<Merchant> getMerchants(Boolean isActive, int limit, int offset) {
//        final String QUERY = isActive ?
//                "SELECT * FROM merchants WHERE is_active = TRUE AND is_hidden = FALSE LIMIT ? OFFSET ?" :
//                "SELECT * FROM merchants WHERE is_active = FALSE AND is_hidden = FALSE LIMIT ? OFFSET ?";
//        return jdbcTemplate.query(
//                QUERY,
//                BeanPropertyRowMapper.newInstance(Merchant.class),
//                limit,
//                offset
//        );
//    }
//
//    public List<Merchant> getMerchantsByUserId(int userId, Boolean isActive, int limit, int offset) {
//        final String QUERY = isActive ?
//                "SELECT * FROM merchants WHERE user_id = ? AND is_active = TRUE AND is_hidden = FALSE LIMIT ? OFFSET ?" :
//                "SELECT * FROM merchants WHERE user_id = ? AND is_active = FALSE AND is_hidden = FALSE LIMIT ? OFFSET ?";
//        return jdbcTemplate.query(
//                QUERY,
//                BeanPropertyRowMapper.newInstance(Merchant.class),
//                userId,
//                limit,
//                offset
//        );
//    }
//
//    public Merchant getMerchantById(int id) {
//        return jdbcTemplate.queryForObject(
//                "SELECT * FROM merchants WHERE id = ? AND is_hidden = FALSE",
//                BeanPropertyRowMapper.newInstance(Merchant.class),
//                id
//        );
//    }
//
//    public Merchant createMerchant(Merchant merchant) {
//        KeyHolder key = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement("INSERT INTO merchants (user_id, identifier, name, name_alt, is_active) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//            ps.setLong(1, merchant.getUserId());
//            ps.setString(2, merchant.getIdentifier());
//            ps.setString(3, merchant.getName());
//            ps.setString(4, merchant.getNameAlt());
//            ps.setBoolean(5, merchant.getIsActive());
//            return ps;
//        }, key);
//        merchant.setId(key.getKey().intValue());
//        return merchant;
//    }
//
//    public Boolean updateMerchant(Merchant merchant) {
//        return jdbcTemplate.update(
//                "UPDATE merchants SET user_id = ?, identifier = ?, name = ?, name_alt = ?, is_active = ? WHERE id = ? AND is_hidden = FALSE",
//                merchant.getUserId(),
//                merchant.getIdentifier(),
//                merchant.getName(),
//                merchant.getNameAlt(),
//                merchant.getIsActive()
//        ) > 0;
//    }
//
//    public Boolean deleteMerchant(Integer id) {
//        return jdbcTemplate.update(
//                "UPDATE merchants SET is_hidden = TRUE WHERE id = ? AND is_hidden = FALSE",
//                id
//        ) > 0;
//    }
}
