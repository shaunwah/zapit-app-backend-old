package com.shaunwah.zapitappbackend.repositories;

import com.shaunwah.zapitappbackend.models.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Transaction> getTransactions(int limit, int offset) {
        return jdbcTemplate.query(
                "SELECT * FROM transactions WHERE is_hidden = FALSE LIMIT ? OFFSET ?",
                BeanPropertyRowMapper.newInstance(Transaction.class),
                limit,
                offset
        );
    }

    public List<Transaction> getTransactionsByUserId(int userId, int limit, int offset) {
        return jdbcTemplate.query(
                "SELECT * FROM transactions WHERE user_id = ? AND is_hidden = FALSE LIMIT ? OFFSET ? ",
                BeanPropertyRowMapper.newInstance(Transaction.class),
                userId,
                limit,
                offset
        );
    }

    public Transaction getTransactionById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM transactions WHERE id = ? AND is_hidden = FALSE",
                BeanPropertyRowMapper.newInstance(Transaction.class),
                id
        );
    }

    public Boolean createTransaction(Transaction transaction) {
        return jdbcTemplate.update(
                "INSERT INTO transactions VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                null,
                transaction.getMerchantStoreId(),
                transaction.getUserId(),
                transaction.getIdentifier(),
                transaction.getStatus(),
                transaction.getIsHidden(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt()
        ) > 0;
    }

    public Boolean updateTransactionStatus(Integer id, String status) {
        return jdbcTemplate.update(
                "UPDATE transactions SET status = ? WHERE id = ? AND is_hidden = FALSE",
                status,
                id
        ) > 0;
    }

    public Boolean deleteTransaction(Integer id) {
        return jdbcTemplate.update(
                "UPDATE transactions SET is_hidden = TRUE WHERE id = ? AND is_hidden = FALSE",
                id
        ) > 0;
    }
}
