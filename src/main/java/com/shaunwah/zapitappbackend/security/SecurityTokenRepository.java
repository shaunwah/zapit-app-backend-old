package com.shaunwah.zapitappbackend.security;

import org.springframework.data.repository.CrudRepository;

public interface SecurityTokenRepository extends CrudRepository<SecurityToken, Long> {
}
