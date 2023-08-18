package com.shaunwah.zapitappbackend.repositories;

import com.shaunwah.zapitappbackend.models.PaymentMethod;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Long> {
    @Query("select pm from PaymentMethod pm")
    @Nonnull
    List<PaymentMethod> findAll();
}
