package com.shaunwah.zapitappbackend.repositories;

import com.shaunwah.zapitappbackend.models.InvoiceStatus;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InvoiceStatusRepository extends CrudRepository<InvoiceStatus, Long> {
    @Query("select is from InvoiceStatus is")
    @Nonnull
    List<InvoiceStatus> findAll();
}
