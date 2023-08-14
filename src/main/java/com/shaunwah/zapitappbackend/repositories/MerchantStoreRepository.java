package com.shaunwah.zapitappbackend.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MerchantStoreRepository {
    private final JdbcTemplate jdbcTemplate;
}
