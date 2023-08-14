package com.shaunwah.zapitappbackend.repositories;

import com.shaunwah.zapitappbackend.models.TransactionDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionDetailsRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<TransactionDetails> getTransactionDetails(int limit, int offset) {
        return jdbcTemplate.query(
                "SELECT * FROM transaction_details WHERE is_hidden = FALSE LIMIT ? OFFSET ?",
                BeanPropertyRowMapper.newInstance(TransactionDetails.class),
                limit,
                offset
        );
    }

    public List<TransactionDetails> getTransactionDetailsByTransactionId(int transactionId, int limit, int offset) {
        return jdbcTemplate.query(
                "SELECT * FROM transaction_details WHERE transaction_id = ? AND is_hidden = FALSE LIMIT ? OFFSET ? ",
                BeanPropertyRowMapper.newInstance(TransactionDetails.class),
                transactionId,
                limit,
                offset
        );
    }

    public TransactionDetails getTransactionDetailsById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM transaction_details WHERE id = ? AND is_hidden = FALSE",
                BeanPropertyRowMapper.newInstance(TransactionDetails.class),
                id
        );
    }

    public Boolean createTransactionDetails(TransactionDetails transactionDetails) {
        return jdbcTemplate.update(
                "INSERT INTO transaction_details VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                null,
                transactionDetails.getTransactionId(),
                transactionDetails.getProductId(),
                transactionDetails.getQuantity(),
                transactionDetails.getTaxRate(),
                transactionDetails.getIsHidden(),
                transactionDetails.getCreatedAt(),
                transactionDetails.getUpdatedAt()
        ) > 0;
    }

    public Boolean deleteTransactionDetails(Integer id) {
        return jdbcTemplate.update(
                "UPDATE transaction_details SET is_hidden = TRUE WHERE id = ? AND is_hidden = FALSE",
                id
        ) > 0;
    }
}
