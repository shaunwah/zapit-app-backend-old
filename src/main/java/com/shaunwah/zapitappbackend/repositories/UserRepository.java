package com.shaunwah.zapitappbackend.repositories;

import com.shaunwah.zapitappbackend.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u where u.isHidden = false")
    List<User> findAll(Pageable pageable);
    @Query("select u from User u where u.id = :userId and u.isHidden = false")
    User findById(long userId);

    @Query("select u from User u where u.username = :username and u.isHidden = false")
    User findByUsername(String username);
    @Modifying
    @Query("update User u set u.isHidden = true where u.id = :userId")
    int deleteById(long userId);
//    private final JdbcTemplate jdbcTemplate;
//
//    public List<User> getUsers(int limit, int offset) {
//        return jdbcTemplate.query( // except password
//                "SELECT id, username, first_name, last_name, date_of_birth, email, mobile_phone, is_merchant," +
//                        "is_active, is_hidden, created_at, updated_at FROM users WHERE is_hidden = FALSE LIMIT ? OFFSET ?",
//                BeanPropertyRowMapper.newInstance(User.class),
//                limit,
//                offset
//        );
//    }
//
//    // TODO get users in a merchant
//
//    public User getUserById(int id) {
//        return jdbcTemplate.queryForObject( // except password
//                "SELECT id, username, first_name, last_name, date_of_birth, email, mobile_phone, is_merchant," +
//                        "is_active, is_hidden, created_at, updated_at FROM users WHERE id = ? AND is_hidden = FALSE",
//                BeanPropertyRowMapper.newInstance(User.class),
//                id
//        );
//    }
//
//    public User createUser(User user) {
//        KeyHolder key = new GeneratedKeyHolder();
//        jdbcTemplate.update(con -> {
//            PreparedStatement ps = con.prepareStatement(
//                    "INSERT INTO users (username, password, first_name, last_name, date_of_birth, email, mobile_phone, is_merchant) " +
//                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
//                    Statement.RETURN_GENERATED_KEYS
//            );
//            ps.setString(1, user.getUsername());
//            ps.setString(2, user.getPassword());
//            ps.setString(3, user.getFirstName());
//            ps.setString(4, user.getLastName());
//            ps.setDate(5, user.getDateOfBirth());
//            ps.setString(6, user.getEmail());
//            ps.setString(7, user.getMobilePhone());
//            ps.setBoolean(8, user.getIsMerchant());
//
//            return ps;
//        }, key);
//        user.setId(key.getKey().intValue());
//        return user;
//    }
//
//    public Boolean updateUser(User user) {
//        return jdbcTemplate.update(
//                "UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ?, date_of_birth = ?," +
//                        "email = ?, mobile_phone = ?, is_merchant = ? WHERE id = ? AND is_hidden = FALSE",
//                user.getUsername(),
//                user.getPassword(),
//                user.getFirstName(),
//                user.getLastName(),
//                user.getDateOfBirth(),
//                user.getEmail(),
//                user.getMobilePhone(),
//                user.getIsMerchant()
//        ) > 0;
//    }
//
//    public Boolean deleteUser(Integer id) {
//        return jdbcTemplate.update(
//                "UPDATE users SET is_hidden = TRUE WHERE id = ? AND is_hidden = FALSE",
//                id
//        ) > 0;
//    }
}
