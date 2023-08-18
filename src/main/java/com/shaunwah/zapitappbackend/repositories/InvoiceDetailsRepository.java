package com.shaunwah.zapitappbackend.repositories;

import com.shaunwah.zapitappbackend.models.Invoice;
import com.shaunwah.zapitappbackend.models.InvoiceDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceDetailsRepository extends CrudRepository<InvoiceDetails, Long> {
    @Query("select id from InvoiceDetails id where id.isHidden = false")
    List<Invoice> findAll(Pageable pageable);
    @Query("select id from InvoiceDetails id where id.id = :invoiceDetailsId and id.isHidden = false")
    Invoice findById(long invoiceDetailsId);
    @Modifying
    @Query("update InvoiceDetails id set id.isHidden = true where id.id = :invoiceDetailsId")
    int deleteById(long invoiceDetailsId);
//    private final JdbcTemplate jdbcTemplate;
//
//    public List<InvoiceDetails> getInvoiceDetails(int limit, int offset) {
//        return jdbcTemplate.query(
//                "SELECT * FROM invoice_details WHERE is_hidden = FALSE LIMIT ? OFFSET ?",
//                BeanPropertyRowMapper.newInstance(InvoiceDetails.class),
//                limit,
//                offset
//        );
//    }
//
//    public List<InvoiceDetails> getInvoiceDetailsByInvoiceId(int invoiceId, int limit, int offset) {
//        return jdbcTemplate.query(
//                "SELECT * FROM invoice_details WHERE invoice_id = ? AND is_hidden = FALSE LIMIT ? OFFSET ?",
//                BeanPropertyRowMapper.newInstance(InvoiceDetails.class),
//                invoiceId,
//                limit,
//                offset
//        );
//    }
//
//    public InvoiceDetails getInvoiceDetailsById(int id) {
//        return jdbcTemplate.queryForObject(
//                "SELECT * FROM invoice_details WHERE id = ? AND is_hidden = FALSE",
//                BeanPropertyRowMapper.newInstance(InvoiceDetails.class),
//                id
//        );
//    }
//
//    public InvoiceDetails createInvoiceDetails(InvoiceDetails invoiceDetails) {
//        KeyHolder key = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement(
//                    "INSERT INTO invoice_details (user_merchant_id, invoice_id, product_id, quantity, tax_rate) VALUES (?, ?, ?, ?, ?)",
//                    Statement.RETURN_GENERATED_KEYS
//            );
//            ps.setInt(1, invoiceDetails.getUserMerchantId());
//            ps.setInt(2, invoiceDetails.getInvoiceId());
//            ps.setInt(3, invoiceDetails.getProductId());
//            ps.setInt(4, invoiceDetails.getQuantity());
//            ps.setDouble(5, invoiceDetails.getTaxRate());
//            return ps;
//        }, key);
//        invoiceDetails.setId(key.getKey().intValue());
//        return invoiceDetails;
//    }
//
//    public int[][] createInvoiceDetails(List<InvoiceDetails> invoiceDetails) {
//        return jdbcTemplate.batchUpdate(
//                "INSERT INTO invoice_details (user_merchant_id, invoice_id, product_id, quantity, tax_rate) VALUES (?, ?, ?, ?, ?)",
//                invoiceDetails,
//                Constants.JDBC_BATCH_SIZE,
//                ((PreparedStatement ps, InvoiceDetails id) -> {
//                    ps.setInt(1, id.getUserMerchantId());
//                    ps.setInt(2, id.getInvoiceId());
//                    ps.setInt(3, id.getProductId());
//                    ps.setInt(4, id.getQuantity());
//                    ps.setDouble(5, id.getTaxRate());
//                })
//        );
//    }
//
//    public Boolean updateInvoiceDetails(InvoiceDetails invoiceDetails) {
//        return jdbcTemplate.update(
//                "UPDATE invoice_details SET user_merchant_id = ?, product_id = ?, quantity = ?, tax_rate = ? WHERE id = ? AND is_locked = FALSE AND is_hidden = FALSE",
//                invoiceDetails.getUserMerchantId(),
//                invoiceDetails.getProductId(),
//                invoiceDetails.getQuantity(),
//                invoiceDetails.getTaxRate(),
//                invoiceDetails.getId()
//        ) > 0;
//    }
//
//    public Boolean updateInvoiceDetailsIsLockedByTransactionId(Integer invoiceId) {
//        Integer count = jdbcTemplate.queryForObject(
//                "SELECT COUNT(*) FROM invoice_details WHERE invoice_id = ? AND is_locked = FALSE AND is_hidden = FALSE",
//                Integer.class,
//                invoiceId
//        );
//
//        return jdbcTemplate.update(
//                "UPDATE invoice_details SET is_locked = TRUE WHERE invoice_id = ? AND is_locked = FALSE AND is_hidden = FALSE",
//                invoiceId
//        ) > 0;
//    }
//
//    public Boolean deleteInvoiceDetailsByInvoiceId(Integer invoiceId) {
//        Integer count = jdbcTemplate.queryForObject(
//                "SELECT COUNT(*) FROM invoice_details WHERE invoice_id = ? AND is_locked = FALSE AND is_hidden = FALSE",
//                Integer.class,
//                invoiceId
//        );
//
//        return jdbcTemplate.update(
//                "UPDATE invoice_details SET is_hidden = TRUE WHERE invoice_id = ? AND is_locked = FALSE AND is_hidden = FALSE",
//                invoiceId
//        ) == count;
//    }
}
