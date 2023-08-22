package com.shaunwah.zapitappbackend.transaction;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Query("select t from Transaction t where t.isHidden = false")
    List<Transaction> findAll(Pageable pageable);
    @Query("select t from Transaction t where t.id = :transactionId and t.isHidden = false")
    Transaction findById(long transactionId);
    @Modifying
    @Query("update Transaction t set t.isHidden = true where t.id = :transactionId")
    int deleteById(long transactionId);
//    private final JdbcTemplate jdbcTemplate;
//
//    public List<Transaction> getTransactions(int limit, int offset) {
//        return jdbcTemplate.query(
//                "SELECT * FROM transactions WHERE is_hidden = FALSE LIMIT ? OFFSET ?",
//                BeanPropertyRowMapper.newInstance(Transaction.class),
//                limit,
//                offset
//        );
//    }
//
//    public List<Transaction> getTransactionsByUserId(int userId, int limit, int offset) {
//        return jdbcTemplate.query(
//                "SELECT * FROM transactions WHERE user_id = ? AND is_hidden = FALSE LIMIT ? OFFSET ?",
//                BeanPropertyRowMapper.newInstance(Transaction.class),
//                userId,
//                limit,
//                offset
//        );
//    }
//
//    public Transaction getTransactionById(int id) {
//        return jdbcTemplate.queryForObject(
//                "SELECT * FROM transactions WHERE id = ? AND is_hidden = FALSE",
//                BeanPropertyRowMapper.newInstance(Transaction.class),
//                id
//        );
//    }
//
//    public Transaction createTransaction(Transaction transaction) {
//        KeyHolder key = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement("INSERT INTO transactions (user_id, invoice_id, identifier, status, payment_method, amount) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//            ps.setInt(1, transaction.getUserId());
//            ps.setInt(2, transaction.getInvoiceId());
//            ps.setString(3, transaction.getIdentifier());
//            ps.setInt(4, transaction.getStatus());
//            ps.setInt(5, transaction.getPaymentMethod());
//            ps.setDouble(6, transaction.getAmount());
//            return ps;
//        }, key);
//        transaction.setId(key.getKey().intValue());
//        return transaction;
//    }
//
//    public Boolean updateTransaction(Transaction transaction) {
//        return jdbcTemplate.update(
//                "UPDATE transactions SET user_id = ?, invoice_id = ?, identifier = ?, status = ?, payment_method = ?, amount = ? WHERE id = ? AND is_hidden = FALSE",
//                transaction.getUserId(),
//                transaction.getInvoiceId(),
//                transaction.getIdentifier(),
//                transaction.getStatus(),
//                transaction.getPaymentMethod(),
//                transaction.getAmount()
//        ) > 0;
//    }
//
//    public Boolean deleteTransaction(Integer id) {
//        return jdbcTemplate.update(
//                "UPDATE transactions SET is_hidden = TRUE WHERE id = ? AND is_hidden = FALSE",
//                id
//        ) > 0;
//    }
}
