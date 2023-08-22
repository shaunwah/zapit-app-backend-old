package com.shaunwah.zapitappbackend.user;

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
    @Query("select u from User u where u.email = :email and u.isHidden = false")
    User findByEmail(String email);
    @Modifying
    @Query("update User u set u.isHidden = true where u.id = :userId")
    int deleteById(long userId);
}
