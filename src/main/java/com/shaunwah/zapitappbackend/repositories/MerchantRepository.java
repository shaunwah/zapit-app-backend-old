package com.shaunwah.zapitappbackend.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MerchantRepository {
    private final JdbcTemplate jdbcTemplate;
}
