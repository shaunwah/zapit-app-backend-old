package com.shaunwah.zapitappbackend.repositories;

import com.shaunwah.zapitappbackend.models.TransactionStatus;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionStatusRepository extends CrudRepository<TransactionStatus, Long> {
    @Query("select ts from TransactionStatus ts")
    @Nonnull
    List<TransactionStatus> findAll();
}
