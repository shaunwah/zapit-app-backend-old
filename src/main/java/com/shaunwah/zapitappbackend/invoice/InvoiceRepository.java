package com.shaunwah.zapitappbackend.invoice;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    @Query("select i from Invoice i where i.isHidden = false")
    List<Invoice> findAll(Pageable pageable);
    @Query("select i from Invoice i where i.id = :invoiceId and i.isHidden = false")
    Invoice findById(long invoiceId);
    @Modifying
    @Query("update Invoice i set i.isHidden = true where i.id = :invoiceId")
    int deleteById(long invoiceId);
//    private final JdbcTemplate jdbcTemplate;
//
//    public List<Invoice> getInvoices(int limit, int offset) {
//        return jdbcTemplate.query(
//                "SELECT * FROM invoices_view WHERE is_hidden = FALSE LIMIT ? OFFSET ?",
//                BeanPropertyRowMapper.newInstance(Invoice.class),
//                limit,
//                offset
//        );
//    }
//
//    public List<Invoice> getInvoicesByMerchantStoreId(int merchantStoreId, int limit, int offset) {
//        return jdbcTemplate.query(
//                "SELECT * FROM invoices_view WHERE merchant_store_id = ? AND is_hidden = FALSE LIMIT ? OFFSET ?",
//                BeanPropertyRowMapper.newInstance(Invoice.class),
//                merchantStoreId,
//                limit,
//                offset
//        );
//    }
//
//    public Invoice getInvoiceById(int id) {
//        return jdbcTemplate.queryForObject(
//                "SELECT * FROM invoices WHERE id = ? AND is_hidden = FALSE",
//                BeanPropertyRowMapper.newInstance(Invoice.class),
//                id
//        );
//    }
//
//    public Invoice createInvoice(Invoice invoice) {
//        KeyHolder key = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement(
//                    "INSERT INTO invoices (user_merchant_id, merchant_store_id, identifier, status) VALUES (?, ?, ?, ?)",
//                    Statement.RETURN_GENERATED_KEYS
//            );
//            ps.setInt(1, invoice.getUserMerchantId());
//            ps.setInt(2, invoice.getMerchantStoreId());
//            ps.setString(3, invoice.getIdentifier());
//            ps.setInt(4, invoice.getStatus());
//            return ps;
//        }, key);
//        invoice.setId(key.getKey().intValue());
//        return invoice;
//    }
//
//    public Boolean updateInvoiceCustomerId(Integer id, Integer customerId) {
//        return jdbcTemplate.update(
//                "UPDATE invoices SET user_customer_id = ? WHERE id = ? AND is_locked = FALSE AND is_hidden = FALSE",
//                customerId,
//                id
//        ) > 0;
//    }
//
//    public Boolean updateInvoiceStatus(Integer id, String status) {
//        return jdbcTemplate.update(
//                "UPDATE invoices SET status = ? WHERE id = ? AND is_locked = FALSE AND is_hidden = FALSE",
//                status,
//                id
//        ) > 0;
//    }
//
//    public Boolean updateInvoiceIsLocked(Integer id) {
//        return jdbcTemplate.update(
//                "UPDATE invoices SET is_locked = TRUE WHERE id = ? AND is_locked = FALSE AND is_hidden = FALSE",
//                id
//        ) > 0;
//    }
//
//    public Boolean deleteInvoice(Integer id) {
//        return jdbcTemplate.update(
//                "UPDATE invoices SET is_hidden = TRUE WHERE id = ? AND is_locked = FALSE AND is_hidden = FALSE",
//                id
//        ) > 0;
//    }
}
